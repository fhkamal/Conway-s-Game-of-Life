import javafx.animation.AnimationTimer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class Model {
    // represent my board
    private val sizeOuter = 80
    private val sizeInner = 55

    private val views = ArrayList<IView>()
    private var board = Array(sizeOuter) { BooleanArray(sizeInner) }

    private var clicked = intArrayOf(-1,-1)
    private var old = clicked
    private var shape = ""
    private var time = 0


    fun draw(){
        val timer: AnimationTimer = object: AnimationTimer(){
            var update = 0L
            override fun handle(now: Long) {
                if(now - update >= 1000000000){
                    time++
                    update = now
                    animate()
                }
            }
        }
        timer.start()
    }

    fun getTime(): Int{
        return time
    }

    fun getBoard(): Array<BooleanArray> {
        return board
    }

    fun clearBoard(){
        board = Array(sizeOuter) { BooleanArray(sizeInner) }
        shape = ""
        clicked = intArrayOf(-1,-1)
        old = clicked
        notifyView()
    }

    fun setClicked(cell: IntArray){
        clicked = cell
    }

    fun getClicked(): IntArray{
        return clicked
    }

    fun setOld(cell: IntArray){
        old = cell
    }

    fun getOld(): IntArray{
        return old
    }

    fun setShape(button: String){
        shape = button
    }

    fun getShape(): String{
        return shape
    }

    // view management
    fun addView(view: IView) {
        views.add(view)
    }

    fun removeView(view: IView) {
        views.remove(view)
    }

    fun addBlock() {
        if (shape == "Block"){
            board[clicked[0]][clicked[1]] = true
            board[clicked[0] + 1][clicked[1]] = true
            board[clicked[0]][clicked[1] + 1] = true
            board[clicked[0] + 1][clicked[1] + 1] = true
        }
        else if (shape == "Beehive"){
            board[clicked[0] + 1][clicked[1]] = true
            board[clicked[0] + 2][clicked[1]] = true
            board[clicked[0] + 1][clicked[1] + 2] = true
            board[clicked[0] + 2][clicked[1] + 2] = true
            board[clicked[0]][clicked[1] + 1] = true
            board[clicked[0] + 3][clicked[1] + 1] = true
        }
        else if (shape == "Blinker"){
            for (i in 0..2) {
                board[clicked[0] + i][clicked[1] + 1] = true
            }
        }
        else if (shape == "Toad"){
            for (i in 0..2){
                board[clicked[0] + 1 + i][clicked[1]] = true
                board[clicked[0] + i][clicked[1] + 1] = true
            }
        }
        else if (shape == "Glider"){
            board[clicked[0]][clicked[1] + 1] = true
            board[clicked[0] + 1][clicked[1] + 2] = true
            for (i in 0..2){
                board[clicked[0] + 2][clicked[1] + i] = true
            }
        }
    }

    fun animate() {
        val bo = Array(sizeOuter) { BooleanArray(sizeInner) }
        for (i in 0..sizeOuter-1){
            for (j in 0..sizeInner-1){
                var check = false
                var count = 0
                for (row in i-1..i+1){
                    for (col in j-1..j+1){
                        if (row < 0 || col < 0 || row > sizeOuter-1 || col > sizeInner-1){
                            continue
                        }
                        if ((i != row || j != col) && board[row][col]){
                            count++
                        }
                    }
                }
                if (count == 3 || (board[i][j] && count == 2)){
                    check = true
                }
                bo[i][j] = check
            }
        }
        board = bo
        notifyView()
    }

    fun notifyView() {
        for (view in views) {
            view.update(this)
        }
    }
}