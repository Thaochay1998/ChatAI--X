package com.chataix.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var inputMessage: EditText
    private lateinit var sendButton: Button
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatContainer = findViewById(R.id.chatContainer)
        inputMessage = findViewById(R.id.inputMessage)
        sendButton = findViewById(R.id.sendButton)
        scrollView = findViewById(R.id.scrollView)

        sendButton.setOnClickListener {
            val query = inputMessage.text.toString().trim()
            if (query.isNotEmpty()) {
                addMessageToChat("Bạn: $query", false)
                inputMessage.setText("")
                handleAIResponse(query)
            }
        }
    }

    private fun addMessageToChat(text: String, isAI: Boolean) {
        val textView = TextView(this).apply {
            this.text = text
            setPadding(24, 16, 24, 16)
            textSize = 16f
        }
        chatContainer.addView(textView)
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    private fun handleAIResponse(query: String) {
        when {
            query.contains("ảnh", true) -> {
                addMessageToChat("ChatAI X: Đang khởi tạo hình ảnh AI cho bạn...", true)
            }
            query.contains("video", true) -> {
                addMessageToChat("ChatAI X: Đang xử lý tạo đoạn video ngắn 10-15 giây...", true)
            }
            else -> {
                addMessageToChat("ChatAI X: Chào bạn, tôi đang sẵn sàng hỗ trợ phân tích và trả lời câu hỏi giống ChatGPT!", true)
            }
        }
    }
}
