package by.oit_videorental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private long id;

    @Column(name = "title")
    @Setter
    @Getter
    private String categoryTitle;

       @JsonBackReference
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "age_constraints_id")
    @Getter
    private AgeConstraint ageConstraint;

//    @ManyToMany(mappedBy = "filmCategories")
//    @Getter
//    private Set<Film> filmSet;


    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void addAgeConstraint(AgeConstraint constraint) {
        this.ageConstraint = constraint;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", ageConstraint=" + ageConstraint +
//                ", filmSet=" + filmSet +
                "}\n";
    }
}
