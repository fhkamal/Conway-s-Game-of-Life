import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.FileInputStream

class ToolbarView(model: Model) : IView, ToolBar() {
    init {
        // add buttons to toolbar
        val block = Button("Block")
        block.graphic = ImageView(Image(FileInputStream(System.getProperty("user.dir") + "\\src\\main\\images\\block.png"), 30.0, 30.0, false, false))
        this.items.add(block)
        block.setOnMouseClicked {
            model.setShape(block.text)
        }
        val beehive = Button("Beehive")
        beehive.graphic = ImageView(Image(FileInputStream(System.getProperty("user.dir") + "\\src\\main\\images\\beehive.png"), 30.0, 30.0, false, false))
        this.items.add(beehive)
        beehive.setOnMouseClicked {
            model.setShape(beehive.text)
        }
        val blinker = Button("Blinker")
        blinker.graphic = ImageView(Image(FileInputStream(System.getProperty("user.dir") + "\\src\\main\\images\\blinker.png"), 30.0, 30.0, false, false))
        this.items.add(blinker)
        blinker.setOnMouseClicked {
            model.setShape(blinker.text)
        }
        val toad = Button("Toad")
        toad.graphic = ImageView(Image(FileInputStream(System.getProperty("user.dir") + "\\src\\main\\images\\toad.png"), 30.0, 30.0, false, false))
        this.items.add(toad)
        toad.setOnMouseClicked {
            model.setShape(toad.text)
        }
        val glider = Button("Glider")
        glider.graphic = ImageView(Image(FileInputStream(System.getProperty("user.dir") + "\\src\\main\\images\\glider.png"), 30.0, 30.0, false, false))
        this.items.add(glider)
        glider.setOnMouseClicked {
            model.setShape(glider.text)
        }
        val clear = Button("Clear")
        clear.graphic = ImageView(Image(FileInputStream(System.getProperty("user.dir") + "\\src\\main\\images\\cancel.png"), 30.0, 30.0, false, false))
        this.items.add(clear)
        clear.setOnMouseClicked {
            model.clearBoard()
        }


    }

    override fun update(model: Model) {
        // update my button state
    }
}