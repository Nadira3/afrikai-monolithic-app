package com.precious.AfrikAI.model.data;

import jakarta.persistence.*;
import com.precious.AfrikAI.model.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DataLabelingSubmission extends Submission {

    @Column(name = "general_rating", nullable = false)
    private Integer generalRating; // 1-5 scale

    @Column(name = "helpfulness_rating", nullable = false)
    private Integer helpfulnessRating; // 1-5 scale

    @Column(name = "honesty_rating", nullable = false)
    private Integer honestyRating; // 1-5 scale

    @Column(name = "harmfulness_rating", nullable = false)
    private Integer harmfulnessRating; // 1-5 scale

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    // Custom constructor for DataLabelingSubmission creation
    public DataLabelingSubmission(
        Integer generalRating,
        Boolean review,
        String additionalNotes,
        Integer helpfulnessRating,
        Integer honestyRating,
        Integer harmfulnessRating,
        Task task
    ){
        super(generalRating, review, additionalNotes);
        this.helpfulnessRating = helpfulnessRating;
        this.honestyRating = honestyRating;
        this.harmfulnessRating = harmfulnessRating;
        this.task = task;
    }
}