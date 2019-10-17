import javax.swing.JSlider;

public class RangeSlider extends JSlider {

	public RangeSlider() {
		setOrientation(HORIZONTAL);
	}
	
	// Resets the UI property to a value from the current look and feel
	@Override
	public void updateUI() {
		setUI(new RangeSliderUI(this));
		updateLabelUIs();
	}
	//Returns the slider's current value from the BoundedRangeModel.
	@Override
	public int getValue() {
		return super.getValue();
	}

	@Override
	public void setValue(int value) {
		if (getValue() == value) {
			return;
		}
		int newValue = Math.min(Math.max(getMinimum(), value), getValue() + getExtent());
		int newExtent = getExtent() + getValue() - newValue;

		getModel().setRangeProperties(newValue, newExtent, getMinimum(), 
				getMaximum(), getValueIsAdjusting());
	}
	
	public int getUpperValue() {
		return getValue() + getExtent();
	}
	
	public void setUpperValue(int value) {
		
		int newExtent = Math.min(Math.max(0, value - getValue()), getMaximum() - getValue());
   
		setExtent(newExtent);
	}
	// constructeur avec les valeur min max 
	public RangeSlider(int max, int min) {
		super(min,max);
		setOrientation(HORIZONTAL);
	}

}