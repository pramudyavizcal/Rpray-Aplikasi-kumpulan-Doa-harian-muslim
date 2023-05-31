package com.pramu.rpray.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.pramu.rpray.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val KEY_NAME = "NAMA"
    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        btnLanjut.setOnClickListener { v: View? ->
            try {
                val nama: String = edt_nama.getText().toString()
                if (nama.isEmpty()) {
                    Toast.makeText(this,"Masukkan nama kamu dahulu :)",Toast.LENGTH_SHORT).show()
                } else{
                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    i.putExtra(KEY_NAME, nama)
                    startActivity(i)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this,"Error, Coba Lagi :(",Toast.LENGTH_SHORT).show()
            }

            InterstitialAd.load(this,
                "ca-app-pub-7496679438317923/2411782982",
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                       // Log.d("TAG", adError.message)
                        mInterstitialAd = null
//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(intent)
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        //Log.d("TAG", "Ad was loaded.")
                        mInterstitialAd = interstitialAd
                        mInterstitialAd?.show(this@LoginActivity)
                    }
                })
//            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//            startActivity(intent)
        }


    }


}