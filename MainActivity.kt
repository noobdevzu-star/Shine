package com.shine.app

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val blue = Color.rgb(22, 135, 255)
    private val bg = Color.rgb(9, 13, 20)
    private val card = Color.rgb(20, 31, 47)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        home()
    }

    private fun text(s:String, size:Float, bold:Boolean=false): TextView =
        TextView(this).apply {
            text=s; textSize=size; setTextColor(Color.WHITE)
            if (bold) setTypeface(null, Typeface.BOLD)
            setPadding(8,8,8,8)
        }

    private fun root(): LinearLayout = LinearLayout(this).apply {
        orientation=LinearLayout.VERTICAL
        setPadding(24,24,24,16)
        setBackgroundColor(bg)
    }

    private fun button(s:String): Button = Button(this).apply {
        text=s; setTextColor(Color.WHITE)
        setBackgroundColor(blue)
    }

    private fun bottomNav(r:LinearLayout) {
        val nav=LinearLayout(this).apply { gravity=Gravity.CENTER }
        listOf("Home","Avatar","Games","Settings").forEach { n ->
            val b=Button(this).apply {
                text=n
                setOnClickListener {
                    when(n) {
                        "Home" -> home()
                        "Avatar" -> avatar()
                        "Games" -> games()
                        "Settings" -> settings()
                    }
                }
            }
            nav.addView(b, LinearLayout.LayoutParams(0,64,1f))
        }
        r.addView(nav, LinearLayout.LayoutParams(-1,72))
    }

    private fun home() {
        val r=root()
        r.addView(text("Shine",30f,true))
        r.addView(text("Play. Explore. Make Friends.",15f))
        val hero=LinearLayout(this).apply {
            orientation=LinearLayout.VERTICAL; setPadding(20,24,20,24); setBackgroundColor(card)
        }
        hero.addView(text("Welcome to Shine!",25f,true))
        hero.addView(text("Your first blocky world is ready.",15f))
        val p=button("▶  Play Block World")
        p.setOnClickListener { game() }
        hero.addView(p)
        r.addView(hero, LinearLayout.LayoutParams(-1,190).apply{topMargin=18})
        r.addView(text("Continue Playing",20f,true), LinearLayout.LayoutParams(-1,-2).apply{topMargin=24})
        r.addView(text("Block World\nBy NoobDev\nGame ID: 10000001\nPlayers: 1     Visits: 0",16f))
        bottomNav(r); setContentView(r)
    }

    private fun games() {
        val r=root()
        r.addView(text("Games",28f,true))
        r.addView(text("1 game available",14f))
        val b=button("Block World\nGame ID: 10000001\n▶ PLAY")
        b.setOnClickListener{game()}
        r.addView(b,LinearLayout.LayoutParams(-1,110).apply{topMargin=20})
        bottomNav(r); setContentView(r)
    }

    private fun avatar() {
        val r=root()
        r.addView(text("Avatar",28f,true))
        r.addView(text("Default",20f,true))
        r.addView(text("□\n\n□  □\n  □\n □ □\n\nNo clothing • No accessories",22f))
        val c=button("Characters  •  Clothing  •  Accessories")
        c.setOnClickListener{Toast.makeText(this,"Avatar editor coming soon",Toast.LENGTH_SHORT).show()}
        r.addView(c)
        bottomNav(r); setContentView(r)
    }

    private fun settings() {
        val r=root()
        r.addView(text("Settings",28f,true))
        r.addView(text("Audio",20f,true))
        r.addView(SeekBar(this).apply{progress=70; max=100})
        r.addView(text("Graphics",20f,true))
        r.addView(Switch(this).apply{text="60 FPS";isChecked=true})
        r.addView(Switch(this).apply{text="Shadows";isChecked=true})
        r.addView(text("Controls",20f,true))
        r.addView(SeekBar(this).apply{progress=50;max=100})
        bottomNav(r); setContentView(r)
    }

    private fun game() {
        val r=root()
        r.addView(text("Block World",28f,true))
        r.addView(text("Game ID: 10000001     Player ID: 000001",13f))
        r.addView(text("\n        ☀\n\n   □      □      □\n □□□  □□□  □□□\n\n        □\n       □ □\n      □   □\n\n   ◯                         ↑",24f))
        r.addView(button("←  EXIT TO HOME").apply{setOnClickListener{home()}})
        setContentView(r)
    }
}
