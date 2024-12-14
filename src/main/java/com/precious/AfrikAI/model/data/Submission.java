package com.precious.AfrikAI.model.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.precious.AfrikAI.model.task.Task;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "general_rating", nullable = false)
    private Integer generalRating; // 1-5 scale
    
    @Column(name = "review", nullable = false)
    private Boolean review; // true = good, false = bad
    
    @Column(name = "additional_notes", length = 1000)
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "task_id") // Join column linking to the Task entity
    private Task task;

    // Custom constructor for submission creation
    public Submission(Integer generalRating, Boolean review, String additionalNotes) {
        this.generalRating = generalRating;
        this.review = review;
        this.additionalNotes = additionalNotes;
    }
}
