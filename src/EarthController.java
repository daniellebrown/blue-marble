package hellofx;



import java.time.DateTimeException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EarthController {

	private BlueMarble model;
	
    @FXML
    private Text Title;

    @FXML
    private TextField Instructions;

    @FXML
    private DatePicker DateField;

    @FXML
    private ImageView EarthImage;

    @FXML
    private Button Button;

    @FXML
    private RadioButton BlackAndWhiteButton;
    
    @FXML
    private RadioButton EnhancedViewButton;

    @FXML
    void BlackAndWhitePressed(ActionEvent event) 
    {
    	Image bwImage = new Image(model.getImage());
    	ImageView bw = new ImageView(bwImage);
    	
    	ColorAdjust colorAdjust = new ColorAdjust();
    	colorAdjust.setBrightness(-1);;
    	bw.setEffect(colorAdjust);
    }

    @FXML
    void EnhancedViewPressed(ActionEvent event) 
    {
    	LocalDate enhancedDate = LocalDate.of(2018,7,1);
    	if(DateField.getValue().isBefore(enhancedDate))
		{
    		model.setEnhanced(true);
    	}
    	else
    	{
    		model.setEnhanced(false);
    	}
    	

    }
    
    @FXML
    void buttonPressed(ActionEvent event) {
    	model.setDate(DateField.toString());
    	model.getImage();
    	model.getCaption();

    }

   
	@FXML
    void dateSelection(ActionEvent event) 
	{
		
		 //read in date selected in datepicker
		 
		 LocalDate selectedDate = DateField.getValue();
    		
		 //get current date
		LocalDate currentDate = LocalDate.now();
		
		//get enhanced data deadline
		LocalDate enhancedDate = LocalDate.of(2018,7,1);
		
    	//check if selected date is greater than current date
		 try {
			 //if selected date is populated, perform checks
			 if(selectedDate != null)
			 {
				
				if(selectedDate.isAfter(currentDate))
				{
					System.out.println("Error. You cannot choose a date in the future. Choose again.");
					
				}
				else
				{
					DateField.setValue(selectedDate);
					//System.out.println("Selected date: " + selectedDate);
				}
				
				if(selectedDate.isBefore(enhancedDate))
				{
					model.setEnhanced(true);
				}
				else
				{
					model.setEnhanced(false);
				}
				 
			 }
			 
		 }catch (Throwable t)
		 	{
			 throw new DateTimeException(selectedDate +" is an invalid value for date-picker");
		 	}
		 }
    

}
