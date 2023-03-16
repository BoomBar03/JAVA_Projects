
public class MVC {

	public static void main(String[] args) {
		
		ConvModel model= new ConvModel();
		view view=new view(model);
		
		ConvController control=new ConvController(model,view);
		
	}

}
