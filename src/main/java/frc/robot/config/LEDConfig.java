package frc.robot.config;

import com.ctre.phoenix6.configs.CANdleConfiguration;

public class LEDConfig {
    
    public int kCANdleCANID = 1;
    CANdleConfiguration candleConfig;

    /** Constructor. */
    public LEDConfig() {
        candleConfig = new CANdleConfiguration();

    }
}
