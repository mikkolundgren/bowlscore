package org.stuntbum.bowlscore.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mikko on 29/02/16.
 */
@Entity
@Table(name = "bowlers")
public class Bowler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "paidwhen", nullable = false)
    private Date payDate;

    @Transient
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Transient
    private String formattedDate;

    public Bowler() {}

    public Bowler(String name, Date payDate) {
        this.name = name;
        this.payDate = payDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
        this.formattedDate = sdf.format(payDate);
    }

    public String getFormattedDate() {
        return sdf.format(this.payDate);
    }
}
