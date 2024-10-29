package tn.esprit.demoprototype.model

data class Message(
    val text: String,
    val isSentByMe: Boolean,
    val time: String = "10:03 AM"
)
