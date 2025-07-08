package com.pucetec.exam2.entities

import javax.persistence.*

@Entity
@Table(name = "parking_spot")
data class ParkingSpot(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val floor: Int,
    val occupied: Boolean = false
)
