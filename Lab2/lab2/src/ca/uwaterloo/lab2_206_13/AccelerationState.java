package ca.uwaterloo.lab2_206_13;

public enum AccelerationState {
	WAITING,
	RISING,
	PEAK,
	FALLING,
	NEGATIVE;	
	
	public AccelerationState process(float delta, float record){
		switch(this){ 
		case WAITING:
			if(delta > 0 && record > 0.75){
				StepCounter.setStart(System.currentTimeMillis()) ;
				return RISING;
			} else{
				return WAITING;
			}
		case RISING:
			if(delta > 0 && record > 2.2){
				return PEAK;
			} else if(delta < 0 && record < 0.75){
				return WAITING;
			} else{
				return RISING;
			}
		case PEAK:
			if (delta < 0 && record < 2.2){
				return FALLING;
			} else {
				return PEAK;
			}
		case FALLING:
			if (delta < 0 && record < 0){
				return NEGATIVE;
			} else if (delta > 0 && record > 0){
				return WAITING;
			} else{
				return FALLING;
			}
		case NEGATIVE:
			if (record > 0){
				StepCounter.incrementSteps2();
				StepCounter.setDuration(System.currentTimeMillis() - StepCounter.getStart());
				if (StepCounter.getDuration() > 300 && StepCounter.getDuration() < 600){
					StepCounter.increment();
				}
				return WAITING;
			} else{
				return NEGATIVE;
			}
			
		default:
				return WAITING;
		
		}
	}
	
	
	
}
