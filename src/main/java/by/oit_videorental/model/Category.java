package by.oit_videorental.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "title")
    @Setter
    @Getter
    private String categoryTitle;

    @ManyToOne
    @JoinColumn(name = "age_constraints_id", referencedColumnName = "id")
    @Getter
    private AgeConstraint ageConstraint;

//    @ManyToMany(mappedBy = "filmCategories")
//    @Getter
//    private Set<Film> filmSet;

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
