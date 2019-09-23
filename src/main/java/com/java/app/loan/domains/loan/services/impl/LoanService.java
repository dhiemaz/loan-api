package com.java.app.loan.domains.loan.services.impl;

import com.java.app.loan.constants.Constant;
import com.java.app.loan.domains.loan.models.LoanRequest;
import com.java.app.loan.domains.loan.models.LoanResponse;
import com.java.app.loan.domains.loan.repositories.LoanRepository;
import com.java.app.loan.domains.loan.services.ILoanService;
import com.java.app.loan.entities.Loan;
import com.java.app.loan.enums.ErrorCode;
import com.java.app.loan.exceptions.InvalidValidationException;
import com.java.app.loan.models.ErrorBaseRS;
import com.java.app.loan.utils.DateTimeUtil;
import com.java.app.loan.utils.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoanService implements ILoanService {

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ErrorUtil errorUtil;

    @Autowired
    @Qualifier("jdbcScheduler")
    Scheduler jdbcScheduler;

    /***
     * createLoan transaction
     * @param loanRequest
     * @return
     */
    @Override
    public LoanResponse createLoan(LoanRequest loanRequest) {
        log.info("creating new loan transaction {}", loanRequest);
        LoanResponse loanResponse = new LoanResponse();
        try {
            // validation
            if (!isValidParam(loanRequest)) {
                throw new InvalidValidationException("one or more parameters cannot filled with zero (0) value.");
            }

            Loan loanData = new Loan();
            loanData.setAmount(loanRequest.getAmount());
            loanData.setTerm(loanRequest.getTerm());
            loanData.setTermType(Constant.TERM_TYPE_DAYS);
            loanData.setInterest(Constant.TERM_INTEREST);
            loanData.setUserId(loanRequest.getUserId());
            loanData.setIssueDate(new Date());
            loanData.setCreatedAt(new Date());
            loanData.setDueDate(DateTimeUtil.getDateAfter(new Date(), loanRequest.getTerm()));
            loanRepository.save(loanData);

            loanResponse.setCode("00");
            loanResponse.setMessage("request successfully processed.");

            //return asyncCallable(() -> loanRepository.save(loanData));
        } catch (InvalidValidationException ie) {
            log.error("failed creating new loan transaction {}", ie);
            ErrorBaseRS error = errorUtil.errorRs(ErrorCode.REQUEST_NOT_VALID);
            loanResponse.setError(error);

        } catch (Exception e) {
            log.error("failed creating new loan transaction {}", e);
            ErrorBaseRS error = errorUtil.errorRs(ErrorCode.SOMETHING_UNEXPECTED_HAPPEN);
            loanResponse.setError(error);

        }

        return loanResponse;
    }

    @Override
    public Flux<Loan> getTransactions(String issueDate, String dueDate, double interest, int term) {
        log.info("get all loan transaction by params");
        Map<String, Date> dateRequest = new HashMap<>();
        fillUpDates(issueDate, dueDate, dateRequest);

        Flux<Loan> defer = Flux.defer(() -> Flux.
                fromIterable(this.loanRepository.findLoanByParams(dateRequest.get("issueDate"), dateRequest.get("dueDate"), interest, term)));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<Loan> getAllTransactions() {
        log.info("get all loan transaction");
        Flux<Loan> defer = Flux.defer(() -> Flux.fromIterable(this.loanRepository.findAll()));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<Loan> getTransactionsByInterest(double interest) {
        log.info("get all loan transaction by interest");
        Flux<Loan> defer = Flux.defer(() -> Flux.fromIterable(this.loanRepository.findByInterest(interest)));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<Loan> getTransactionsByTerm(int term) {
        log.info("get all loan transaction by term");
        Flux<Loan> defer = Flux.defer(() -> Flux.fromIterable(this.loanRepository.findByTerm(term)));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<Loan> getTransactionsByIssueDateAndDueDate(String issueDate, String dueDate) {
        log.info("get all loan transaction by issue date and due date");
        Map<String, Date> dateRequest = new HashMap<>();
        fillUpDates(issueDate, dueDate, dateRequest);

        Flux<Loan> defer = Flux.defer(() -> Flux.
                fromIterable(this.loanRepository.findByIssueDateAndDueDate(dateRequest.get("issueDate"), dateRequest.get("dueDate"))));
        return defer.subscribeOn(jdbcScheduler);
    }

    /***
     * isValidParam
     * @param loanRequest
     * @return boolean
     * @throws Exception
     */
    private boolean isValidParam(LoanRequest loanRequest) throws Exception {
        if (loanRequest.getTerm() == 0 || loanRequest.getAmount() == BigDecimal.ZERO){
            return false;
        }
        return true;
    }

    /***
     * fillUpDates
     * @param issueDate
     * @param dueDate
     * @param dateRequest
     */
    private void fillUpDates(String issueDate, String dueDate, Map<String, Date> dateRequest)  {
        Date startDate = null, endDate = null;
        try {
            Boolean issueDateEmpty = StringUtils.isBlank(issueDate);
            Boolean dueDateEmpty = StringUtils.isBlank(dueDate);
            DateFormat dateFormat = new SimpleDateFormat(Arrays.asList(Constant.DATETIME_FORMAT).get(0));

            if (issueDateEmpty == true && dueDateEmpty == true) {
                endDate = dateFormat.parse(dateFormat.format(new Date()));
                startDate = DateUtils.addDays(endDate, -Constant.MAX_DATE_RANGE_DAYS);
            } else if (issueDateEmpty == true && dueDateEmpty == false) {
                endDate = DateUtils.parseDate(dueDate, Constant.DATETIME_FORMAT);
                startDate = DateUtils.addDays(endDate, -Constant.MAX_DATE_RANGE_DAYS);
            } else if (issueDateEmpty == false && dueDateEmpty == true) {
                endDate = dateFormat.parse(dateFormat.format(new Date()));
                startDate = DateUtils.parseDate(issueDate, Constant.DATETIME_FORMAT);
            } else {
                endDate = DateUtils.parseDate(dueDate, Constant.DATETIME_FORMAT);
                startDate = DateUtils.parseDate(issueDate, Constant.DATETIME_FORMAT);
            }
        } catch (Exception e) {
            log.warn(String.format("failed parsing date from request, error : %s", e));
        }

        dateRequest.put("issueDate", startDate);
        dateRequest.put("dueDate", endDate);
    }
}
