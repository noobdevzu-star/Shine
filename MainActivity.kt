package com.shine.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val bg = Color.rgb(18, 18, 20)
    private val fg = Color.WHITE
    private val muted = Color.rgb(190, 190, 190)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val scroll = ScrollView(this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 40, 32, 40)
            setBackgroundColor(bg)
        }

        val title = TextView(this).apply {
            text = "SHINE"
            textSize = 32f
            setTextColor(fg)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Android game-platform prototype"
            textSize = 15f
            setTextColor(muted)
            gravity = Gravity.CENTER
        }

        root.addView(title, matchWrap())
        root.addView(subtitle, matchWrap(0, 0, 0, 28))

        addSectionTitle(root, "HOME")

        val gamesButton = Button(this).apply {
            text = "GAMES"
            setOnClickListener { showGames() }
        }
        root.addView(gamesButton, matchWrap(0, 0, 0, 12))

        val avatarButton = Button(this).apply {
            text = "AVATAR"
            setOnClickListener {
                Toast.makeText(this@MainActivity, "Default avatar", Toast.LENGTH_SHORT).show()
            }
        }
        root.addView(avatarButton, matchWrap(0, 0, 0, 12))

        val settingsButton = Button(this).apply {
            text = "SETTINGS"
            setOnClickListener { showSettings() }
        }
        root.addView(settingsButton, matchWrap())

        scroll.addView(root)
        setContentView(scroll)
    }

    private fun showGames() {
        val root = baseRoot()

        addTitle(root, "GAMES")

        val game = Button(this).apply {
            text = "BLOCK WORLD"
            setOnClickListener {
                Toast.makeText(this@MainActivity, "Game ID: 10000001", Toast.LENGTH_SHORT).show()
            }
        }
        root.addView(game, matchWrap(0, 20, 0, 12))

        val back = Button(this).apply {
            text = "BACK"
            setOnClickListener { showHome() }
        }
        root.addView(back, matchWrap())

        setContentView(root)
    }

    private fun showSettings() {
        val root = baseRoot()

        addTitle(root, "SETTINGS")

        val label = TextView(this).apply {
            text = "Sensitivity"
            textSize = 16f
            setTextColor(fg)
        }
        root.addView(label, matchWrap(0, 20, 0, 4))

        val value = TextView(this).apply {
            text = "80"
            textSize = 14f
            setTextColor(muted)
        }
        root.addView(value, matchWrap(0, 0, 0, 8))

        val seek = SeekBar(this).apply {
            max = 100
            progress = 80
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
        root.addView(seek, matchWrap(0, 0, 0, 20))

        val sound = Switch(this).apply {
            text = "Sound"
            setTextColor(fg)
            isChecked = true
        }
        root.addView(sound, matchWrap(0, 0, 0, 12))

        val back = Button(this).apply {
            text = "BACK"
            setOnClickListener { showHome() }
        }
        root.addView(back, matchWrap())

        setContentView(root)
    }

    private fun baseRoot(): LinearLayout =
        LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 40, 32, 40)
            setBackgroundColor(bg)
        }

    private fun addTitle(root: LinearLayout, text: String) {
        val title = TextView(this).apply {
            this.text = text
            textSize = 28f
            setTextColor(fg)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        root.addView(title, matchWrap())
    }

    private fun addSectionTitle(root: LinearLayout, text: String) {
        val label = TextView(this).apply {
            this.text = text
            textSize = 14f
            setTextColor(muted)
            typeface = Typeface.DEFAULT_BOLD
        }
        root.addView(label, matchWrap(0, 0, 0, 8))
    }

    private fun matchWrap(
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
    ): LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(left, top, right, bottom)
        }
}
