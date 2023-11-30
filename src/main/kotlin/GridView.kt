import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GridView(model: Model) : IView, GridPane(){
    init {
        this.isGridLinesVisible = true
        this.hgap = 0.5
        this.vgap = 0.5
        for (i in 0..74){
            for (j in 0..49){
                val rect = Rectangle(12.0, 12.0)
                rect.fill = Color.WHITE
                rect.setOnMouseClicked {
                    if (model.getShape() != "" && rect.fill == Color.WHITE){
                        model.setClicked(intArrayOf(i, j))
                        model.addBlock()
                        model.notifyView()
                    }
                }
                this.add(rect, i ,j)
            }
        }
    }

    override fun update(model:Model) {
        val board = model.getBoard()
        this.isGridLinesVisible = true
        this.hgap = 0.5
        this.vgap = 0.5
        for (i in 0..74){
            for (j in 0..49){
                val rect = this.children[i*50 + j + 1] as Rectangle
                if (board[i][j] == false && rect.fill == Color.BLACK) {
                    rect.fill = Color.WHITE
                }
                else if (board[i][j] && rect.fill == Color.WHITE){
                    rect.fill = Color.BLACK
                }
            }
        }
    }
}
