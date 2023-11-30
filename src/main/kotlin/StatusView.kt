import javafx.scene.control.TextArea
import javafx.scene.layout.HBox

class StatusView(model: Model) : IView, HBox() {
    init {
        val status = TextArea()
        val frame = TextArea()
        this.children.addAll(status, frame)
        model.draw()
    }

    override fun update(model:Model) {
        if (model.getShape() != "" && model.getClicked() != model.getOld()){
            val cell = model.getClicked()
            this.children[0] = TextArea("Created ${model.getShape()} at ${cell[0]}, ${cell[1]}")
            model.setOld(cell)
        }
        val frame = TextArea("Frame ${model.getTime()}")
        this.children[1] = frame
    }
}