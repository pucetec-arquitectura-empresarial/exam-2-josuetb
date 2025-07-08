
package com.pucetec.exam2.services

import com.pucetec.exam2.repository.ParkingSpotRepository
import com.pucetec.exam2.exceptions.NoAvailableSpotException
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ParkingService(private val parkingSpotRepository: ParkingSpotRepository) {

    fun getAvailability(): Map<String, Any> {
        val totalFree = parkingSpotRepository.countByOccupiedFalse()
        val freeByFloor = parkingSpotRepository.countFreeByFloor().associate { arr ->
            val floor = arr[0] as Int
            val count = (arr[1] as Number).toInt()
            floor to count
        }
        return mapOf(
            "totalFree" to totalFree,
            "freeByFloor" to freeByFloor
        )
    }

    fun assignParkingSpot(): Map<String, Any> {
        val freeSpots = parkingSpotRepository.findAll()
            .filter { !it.occupied }
            .sortedBy { it.floor }
        val spot = freeSpots.firstOrNull()
        if (spot != null) {
            val updated = spot.copy(occupied = true)
            parkingSpotRepository.save(updated)
            val ticketId = UUID.randomUUID().toString()
            return mapOf("ticketId" to ticketId, "spot" to updated)
        }
        throw NoAvailableSpotException()
    }
}

