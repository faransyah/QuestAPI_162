package com.example.p12.repository

import android.os.Parcel
import android.os.Parcelable
import com.example.p12.model.Mahasiswa
import com.example.p12.service_api.MahasiswaService

interface MahasiswaRepository{
    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswabyId(nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(
    private val mahasiswaApiService: MahasiswaService
): MahasiswaRepository {
    constructor(parcel: Parcel) : this(TODO("mahasiswaApiService")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NetworkMahasiswaRepository> {
        override fun createFromParcel(parcel: Parcel): NetworkMahasiswaRepository {
            return NetworkMahasiswaRepository(parcel)
        }

        override fun newArray(size: Int): Array<NetworkMahasiswaRepository?> {
            return arrayOfNulls(size)
        }
    }

    override suspend fun getMahasiswabyId(nim: String): Mahasiswa {
        mahasiswaApiService.getAllMahasiswa()
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> {
        return mahasiswaApiService.getAllMahasiswa(nim)
    }
}