package org.stuntbum.bowlscore.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mikko on 30/12/15.
 */
@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "date", nullable = false)
    private Date date;

    @Transient
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Transient
    private String formattedDate;

    public Score() {}

    public Score (int score, Date date) {
        this.score = score;
        this.date = date;
        this.formattedDate = sdf.format(date);
    }

    public Score (String name, int score, Date date) {
        this.name = name;
        this.score = score;
        this.date = date;
        this.formattedDate = sdf.format(date);
    }
    public Score (Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.formattedDate = sdf.format(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormattedDate() {
        return sdf.format(this.date);
    }

}
