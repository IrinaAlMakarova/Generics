package ru.netology

class NoteNotFoundException : Exception()
data class Note(
    var id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val text: String = "",
    val date: Int = 0,
    val comment: Int = 0,
    val readComments: Int = 0,
    val viewUrl: String = "",
    val privacyView: String = "",
    val canComment: Int = 0,
    val textWiki: String = ""
)

class Donut
data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String = "",
    var donut: Donut = Donut(),
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    var noteId: Int = 0, // Идентификатор заметки
    var thread: Thread = Thread()
)

interface NoteService {
    fun add(title: String, text: String): Int
    fun createComment(noteId: Int, message: String): Int
    fun delete(noteId: Int): Int
    fun deleteComment(commentId: Int): Int //Positive
    fun edit(noteId: Int, title: String, text: String): Int
    fun editComment(commentId: Int, message: String): Int //Positive
    fun get(): List<Note>
    fun getById(noteId: Int): Note//Positive
    fun getComments(noteId: Int): List<Comment>//Positive
    fun restoreComment(commentId: Int): Int//Positive
}


class NoteServiceImpls : NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var commentsDelete = mutableListOf<Comment>()

    private var nId = 0
    private var cId = 0

    override fun add(title: String, text: String): Int {
        val note = Note(id = nId++, title = title, text = text)
        notes.add(note)
        return notes.lastIndex
    }

    override fun createComment(noteId: Int, message: String): Int {
        if (notes[noteId] in notes) {
            val comment = Comment(id = cId++, text = message, noteId = noteId)
            comments.add(comment)
            return comments.lastIndex
        }
        throw NoteNotFoundException()
    }

    override fun delete(noteId: Int): Int {
        if (notes[noteId] in notes) {
            notes.removeAt(noteId)
            return 1
        }
        throw NoteNotFoundException()
    }

    override fun deleteComment(commentId: Int): Int {
        if (comments[commentId] in comments) {
            commentsDelete.add(comments.get(commentId)) // Добавление удаленного комментария в список Удаленных
            comments.removeAt(commentId)
            return 1
        }
        throw NoteNotFoundException()
    }

    override fun edit(noteId: Int, title: String, text: String): Int {
        if (notes[noteId] in notes) {
            notes.removeAt(noteId)
            notes[noteId] = notes[noteId].copy(title = title, text = text)
            notes.add(notes[noteId])
            return 1
        }
        throw NoteNotFoundException()
    }

    override fun editComment(commentId: Int, message: String): Int {
        if (comments[commentId] in comments) {
            comments.removeAt(commentId)
            comments[commentId] = comments[commentId].copy(text = message)
            comments.add(comments[commentId])
            return 1
        }
        throw NoteNotFoundException()
    }

    override fun get(): List<Note> {
        return notes
    }

    override fun getById(noteId: Int): Note {
        if (notes[noteId] in notes) {
            return notes[noteId]
        }
        throw NoteNotFoundException()
    }

    override fun getComments(noteId: Int): List<Comment> {
        if (notes[noteId] in notes) {
            return comments.filter { it.noteId == noteId }
        }
        throw NoteNotFoundException()
    }

    override fun restoreComment(commentId: Int): Int {
        if (commentsDelete.size != 0) {
            if (commentsDelete.get(commentId) in commentsDelete) {
                comments.add(comments[commentId])
            }
            return 1
        }
        throw NoteNotFoundException()
    }

    fun printNote() {
        for (note in notes) {
            println(note)
        }
    }

    fun printComments() {
        for (comment in comments) {
            println(comment)
        }
    }

}

fun main() {
    val noteService = NoteServiceImpls()

    println("Создение заметки")
    val note1 = noteService.add("Заголовок1", "Текст заметки1")
    val note2 = noteService.add("Заголовок2", "Текст заметки12")
    println(note2)
    val note3 = noteService.add("Заголовок3", "Текст заметки13")
    println(note3)
    noteService.printNote()

    println("\nДобавление комментария к заметке")
    val comment1 = noteService.createComment(0, "Текст комментария1")
    val comment2 = noteService.createComment(1, "Текст комментария2")
    val comment3 = noteService.createComment(2, "Текст комментария3")
    println(comment3)
    val comment4 = noteService.createComment(2, "Текст комментария4")
    println(comment4)
    noteService.printComments()

    println("\nУдаление заметки")
    println(noteService.delete(0))
    noteService.printNote()

    println("\nУдаление комментария к заметке")
    noteService.printComments()
    println(noteService.deleteComment(1))
    noteService.printComments()

    println("\nВозвращение списка заметок")
    noteService.get()
    println(noteService.get())

    println("\nВозвращение заметки по её id")
    noteService.getById(0)
    println(noteService.getById(0))
    noteService.getById(1)
    println(noteService.getById(1))

    println("\nВозвращение списка комментариев к заметке") //!!!
    noteService.getComments(0)
    println(noteService.getComments(0))

    println("\nВосстанавление удалённого комментария")
    noteService.printComments()
    println(noteService.restoreComment(0))
    noteService.printComments()
}
