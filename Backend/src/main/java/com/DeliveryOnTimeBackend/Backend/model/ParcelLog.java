package com.DeliveryOnTimeBackend.Backend.model;

import com.DeliveryOnTimeBackend.Backend.extras.ParcelStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @OneToOne
    @JoinColumn(name = "parcelId",referencedColumnName = "parcelId")
    private Parcel parcelId;

    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    private String placementDate;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

   //@ManyToOne
   // @JoinColumn(name = "updatedByUserId")
   // private User updatedBy;

    //  @ManyToOne
    // @JoinColumn(name = "currentRider",referencedColumnName = "userId")
    //private Rider currentRider;
    private String deliveredDate;


}
