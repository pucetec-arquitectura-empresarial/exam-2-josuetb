package com.pucetec.exam2.repository

import com.pucetec.exam2.entities.ParkingSpot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotRepository : JpaRepository<ParkingSpot, Long> {
    fun countByOccupiedFalse(): Long
    @Query("SELECT floor, COUNT(*) FROM ParkingSpot WHERE occupied = false GROUP BY floor")
    fun countFreeByFloor(): List<Array<Any>>
}
