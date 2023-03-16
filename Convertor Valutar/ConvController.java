import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ConvController {
	private ConvModel model;
	private view view;

	public ConvController(ConvModel model1, view view1) {
		this.model = model1;
		this.view = view1;
		view.addSumComboBoxListener(new SumComboBoxListener());
		view.addRezComboBoxListener(new RezComboBoxListener());
		view.addConvButtonListener(new ConvButton());
	}

	class SumComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			model.setCoin1(view.getCoin1());
			JPanel p = new JPanel();
			p = view.getPanel1();
			System.out.println(model.getCoin1());
			p.removeAll();
			p.add(new JLabel(model.getCoin1()));
			p=view.getUpper();
			p.removeAll();
			model.convert(view.getCoin1(), view.getCoin2(), (float) 1);
			String str=new String();
			str=Float.toString(model.getResult());
			p.add(new JLabel(view.getCoin1()+" = "+str+" "+view.getCoin2()));
			SwingUtilities.updateComponentTreeUI(view.getFrame());
			
		}

	}

	class RezComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setCoin2(view.getCoin2());
			JPanel p = new JPanel();
			p = view.getPanel2();
			System.out.println(model.getCoin2());
			p.removeAll();
			p.add(new JLabel(model.getCoin2()));
			p=view.getUpper();
			p.removeAll();
			model.convert(view.getCoin1(), view.getCoin2(), (float) 1);
			String str=new String();
			str=Float.toString(model.getResult());
			p.add(new JLabel(view.getCoin1()+" = "+str+" "+view.getCoin2()));
			SwingUtilities.updateComponentTreeUI(view.getFrame());

		}

	}

	class ConvButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			float input = view.getUserInput();
			String fromCurrency = view.getCoin1();
			String finalCurrency = view.getCoin2();
			
			model.convert(fromCurrency, finalCurrency, input);
			
			//System.out.println(input);
			System.out.println(model.getResult());
			view.setValue(model.getResult());
			
			
		}

	}

}
