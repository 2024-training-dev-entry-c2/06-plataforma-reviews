package restaurant.controller.review;

import restaurant.controller.interfaces.IController;
import restaurant.Interface.IReview;
import restaurant.service.review.AddReview;

public class AddReviewController implements IController {
    private final AddReview command;

    public AddReviewController(AddReview command) {
        this.command = command;
    }

    @Override
    public void execute() {
        IReview review = command.execute();
        if (review != null) {
            System.out.println("Se agregó la reseña:");
            System.out.println("Comentario: " + review.getComment());
            System.out.println("Calificación: " + review.getQualification());
        }
    }
}