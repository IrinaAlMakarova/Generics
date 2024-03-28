package ru.netology

import ru.netology.NoteServise.add

class Note(
    val title: String,
    val text: String,
    val privacy: Int,
    val commentPrivacy: Int,
    val privacyView: String,
    val privacyComment: String
) {
    var nid: Int = 0
}

class Comment(
    val noteId: String,
    val ownerId: Long, //Positive,
    val replyTo: Long, //Positive,
    val message: String,
    val guid: String
) {
    var cid: Int = 0
}

object NoteServise {
    private var notes = emptyArray<Note>()
    private var comments = emptyArray<Comment>()
    var nid: Int = 0
    var cid: Int = 0

    fun add(note: Note): Int {
        notes += note
        nid = notes.lastIndex
        return nid
    }

    fun createComment(postId: Int, comment: Comment): Int {
        for (note in notes) {
            if (note.nid == postId) {
                comments += comment
                cid = comments.lastIndex
            }
        }
        return cid
    }
}


fun main() {
    var note = Note("Title1", "Text1", 1, 1, "all", "all")
    println("Создание новой заметки (возвращает идентификатор созданной заметки) - " + add(note))
    note = Note("Title2", "Text2", 2, 2, "all", "all")
    println("Создание новой заметки (возвращает идентификатор созданной заметки) - " + add(note))

    var comment = Comment("1", 1, 1, "massege1", "guid1")
    println(
        "Добавление нового комментария к заметке (возвращает идентификатор созданного комментария) - " + NoteServise.createComment(
            1,
            comment
        )
    )
    comment = Comment("2", 2, 2, "massege2", "guid2")
    println(
        "Добавление нового комментария к заметке (возвращает идентификатор созданного комментария) - " + NoteServise.createComment(
            0,
            comment
        )
    )
    comment = Comment("3", 3, 3, "massege3", "guid3")
    println(
        "Добавление нового комментария к заметке (возвращает идентификатор созданного комментария) - " + NoteServise.createComment(
            3,
            comment
        )
    )


}