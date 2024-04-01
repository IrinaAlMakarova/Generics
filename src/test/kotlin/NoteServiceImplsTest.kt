import org.junit.Test

import org.junit.Assert.*
import ru.netology.NoteServiceImpls

class NoteServiceImplsTest {
    val noteService = NoteServiceImpls()

    @Test
    fun add1() {
        val result = noteService.add("Заголовок1", "Текст заметки1")
        assertEquals(result, 0)
    }

    @Test
    fun add2() {
        noteService.add("Заголовок1", "Текст заметки1")
        val result = noteService.add("Заголовок2", "Текст заметки2")
        assertEquals(result, 1)
    }

    @Test
    fun add3() {
        val result = noteService.add("Заголовок3", "Текст заметки3")
        assertEquals(result, 3)
    }

    @Test
    fun createComment1() {
        noteService.add("Заголовок1", "Текст заметки1")
        noteService.add("Заголовок2", "Текст заметки2")
        val result = noteService.createComment(0, "Текст комментария1")
        assertEquals(result, 0)
    }

    @Test
    fun createComment2() {
        noteService.add("Заголовок1", "Текст заметки1")
        noteService.add("Заголовок2", "Текст заметки12")

        noteService.createComment(0, "Текст комментария1")
        noteService.createComment(1, "Текст комментария2")
        val result = noteService.createComment(1, "Текст комментария3")
        assertEquals(result, 2)
    }

    @Test
    fun createComment3() {
        noteService.add("Заголовок1", "Текст заметки1")
        noteService.add("Заголовок2", "Текст заметки12")

        noteService.createComment(0, "Текст комментария1")
        noteService.createComment(1, "Текст комментария2")
        val result = noteService.createComment(1, "Текст комментария3")
        assertEquals(result, 3)
    }

    @Test
    fun createComment4() {
        noteService.add("Заголовок1", "Текст заметки1")
        noteService.add("Заголовок2", "Текст заметки12")

        noteService.createComment(0, "Текст комментария1")
        noteService.createComment(1, "Текст комментария2")
        val result = noteService.createComment(5, "Текст комментария3")
        assertEquals(result, 3) // Исключение
    }

    @Test
    fun delete1() {
        noteService.add("Заголовок1", "Текст заметки1")
        noteService.add("Заголовок2", "Текст заметки12")

        val result = noteService.delete(0)
        assertEquals(result, 1)
    }

    @Test
    fun delete2() {
        noteService.add("Заголовок1", "Текст заметки1")
        noteService.add("Заголовок2", "Текст заметки12")

        val result = noteService.delete(3)
        assertEquals(result, 1) // Исключение
    }

}