package io.github.vladzasoba.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "atransaction", schema = "test")
public class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "tx_date")
    private Date transacationDate;
    @ManyToOne
    @JoinColumn(name = "src_account_id")
    private Account srcAccount;
    @ManyToOne
    @JoinColumn(name = "dst_account_id")
    private Account dstAccount;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "transaction_type")
    private String transactionType;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransacationDate() {
        return transacationDate;
    }

    public void setTransacationDate(Date transacationDate) {
        this.transacationDate = transacationDate;
    }

    public Account getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(Account srcAccount) {
        this.srcAccount = srcAccount;
    }

    public Account getDstAccount() {
        return dstAccount;
    }

    public void setDstAccount(Account dstAccount) {
        this.dstAccount = dstAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transacationDate, that.transacationDate) &&
                Objects.equals(srcAccount, that.srcAccount) &&
                Objects.equals(dstAccount, that.dstAccount) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionType, that.transactionType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(transacationDate, srcAccount, dstAccount, amount, transactionType);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transacationDate=" + transacationDate +
                ", srcAccount=" + srcAccount +
                ", dstAccount=" + dstAccount +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}

