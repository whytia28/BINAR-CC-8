package com.example.binarchapter7.logic


class Controler {
    companion object {
        val pilihanGame = listOf("batu", "kertas", "gunting")
        var pilihanCpu = ""
    }


    fun caraMain(
        pilihanSatu: String?, pilihanDua: String?
    ): String {
        return if (pilihanSatu == pilihanGame[0] && pilihanDua == pilihanGame[2]
            || pilihanSatu == pilihanGame[2] && pilihanDua == pilihanGame[1]
            || pilihanSatu == pilihanGame[1] && pilihanDua == pilihanGame[0]
        ) "pemain 1 menang"
        else if (pilihanDua == pilihanGame[0] && pilihanSatu == pilihanGame[2]
            || pilihanDua == pilihanGame[2] && pilihanSatu == pilihanGame[1]
            || pilihanDua == pilihanGame[1] && pilihanSatu == pilihanGame[0]
        ) "pemain 2 menang"
        else "draw"
    }

    fun caraMainCpu(pilihanSatu: String?): String {
        pilihanCpu = pilihanGame.random()
        return if (pilihanSatu == pilihanGame[0] && pilihanCpu == pilihanGame[2]
            || pilihanSatu == pilihanGame[2] && pilihanCpu == pilihanGame[1]
            || pilihanSatu == pilihanGame[1] && pilihanCpu == pilihanGame[0]
        ) "pemain 1 menang"
        else if (pilihanCpu == pilihanGame[0] && pilihanSatu == pilihanGame[2]
            || pilihanCpu == pilihanGame[2] && pilihanSatu == pilihanGame[1]
            || pilihanCpu == pilihanGame[1] && pilihanSatu == pilihanGame[0]
        ) "CPU 2 menang"
        else "draw"
    }
}