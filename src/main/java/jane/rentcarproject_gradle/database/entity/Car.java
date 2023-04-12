package jane.rentcarproject_gradle.database.entity;

import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "bookings")
@EqualsAndHashCode(exclude = "bookings")
@Builder
@Entity
@Table(name = "cars", schema = "public")
public class Car implements BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private CarColorEnum color;

    @Column(name = "seat_amount")
    private Integer seatAmount;

    @Column(name = "price_per_day")
    private Integer pricePerDay;

    @Enumerated(EnumType.STRING)
    private CarStatusEnum status;

    @Column(name = "image")
    private String image;

    @Builder.Default
    @OneToMany(mappedBy = "car")
    private List<Booking> bookings = new ArrayList<>();
}
