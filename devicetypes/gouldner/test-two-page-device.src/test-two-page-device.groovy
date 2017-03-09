/**
 *  TestSlider
 *
 *  Copyright 2015 Ronald Gouldner
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
 
preferences {
    input("testOnOff", "enum", title: "Test Enum Preference ?", default:"On", options: ["On","Off"])
}

metadata {
	definition (name: "Test Two Page Device", namespace: "gouldner", author: "Ronald Gouldner") {
		command "setTestValue"
		command "reportTestValue"
        command "setTestValue2"
		command "reportTestValue2"
        command "swingModeOff"
        command "swingModeOn"
        command "reset"
        command "actionA"
        command "actionB"
        command "actionC"
        command "actionD"
        command "actionE"
        command "actionF"
        command "actionG"
        command "actionH"
        command "actionI"
        
		attribute "testValue", "NUMBER"
		attribute "testValue2", "NUMBER"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
    
        // Swing mode Off.
        standardTile("swingOff", "swingOff", inactiveLabel: false, decoration: "flat") {
            state "off", action:"swingModeOff", icon:"st.secondary.refresh-icon", label: 'Swing Off'
        }
        // Swing mode On.
        standardTile("swingOn", "swingOn", inactiveLabel: false, decoration: "flat") {
            state "on", action:"swingModeOn", icon:"st.secondary.refresh-icon", label: 'Swing On'
        }
        // Reset.
        standardTile("reset", "reset", inactiveLabel: false, decoration: "flat") {
            state "reset", action:"reset", icon:"st.secondary.refresh-icon", label: 'Reset'
        }
        // Action A
        standardTile("actiona", "actiona", inactiveLabel: false, decoration: "flat") {
            state "a", action:"actionA", icon:"st.secondary.refresh-icon", label: 'Action A'
        }
        // Action B
        standardTile("actionb", "actionb", inactiveLabel: false, decoration: "flat") {
            state "b", action:"actionB", icon:"st.secondary.refresh-icon", label: 'Action B'
        }
        // Action C
        standardTile("actionc", "actionc", inactiveLabel: false, decoration: "flat") {
            state "c", action:"actionC", icon:"st.secondary.refresh-icon", label: 'Action C'
        }
        
        // Action D
        standardTile("actiond", "actiond", inactiveLabel: false, decoration: "flat") {
            state "d", action:"actionD", icon:"st.secondary.refresh-icon", label: 'Action D'
        }
        // Action E
        standardTile("actione", "actione", inactiveLabel: false, decoration: "flat") {
            state "e", action:"actionE", icon:"st.secondary.refresh-icon", label: 'Action E'
        }
        // Action F
        standardTile("actionf", "actionf", inactiveLabel: false, decoration: "flat") {
            state "f", action:"actionF", icon:"st.secondary.refresh-icon", label: 'Action F'
        }
        
        // Action G
        standardTile("actiong", "actiong", inactiveLabel: false, decoration: "flat") {
            state "g", action:"actionG", icon:"st.secondary.refresh-icon", label: 'Action G'
        }
        // Action H
        standardTile("actionh", "actionh", inactiveLabel: false, decoration: "flat") {
            state "h", action:"actionH", icon:"st.secondary.refresh-icon", label: 'Action H'
        }
        // Action I
        standardTile("actioni", "actioni", inactiveLabel: false, decoration: "flat") {
            state "i", action:"actionI", icon:"st.secondary.refresh-icon", label: 'Action I'
        }
        
		valueTile("value", "testValue", inactiveLabel: false, decoration: "flat") {
			state "value", action:"reportTestValue", label:'${currentValue}', unit:""
		}
		controlTile("valueSliderControl", "testValue", "slider", height: 1, width: 2, inactiveLabel: false, range:"(67..84)") {
			state "valueSliderControl", action:"setTestValue", backgroundColor:"#d04e00"
		}
		valueTile("value2", "testValue2", inactiveLabel: false, decoration: "flat") {
			state "value2", action:"reportTestValue2", label:'${currentValue}', unit:""
		}
		controlTile("value2SliderControl", "testValue2", "slider", height: 1, width: 2, inactiveLabel: false) {
			state "value2SliderControl", action:"setTestValue2", backgroundColor:"#00ff00"
		}
		
		main (["value"])
		details(["value", "valueSliderControl",
                 "value2","value2SliderControl",
                 "swingOff","swingOn","reset",
                 "actiona","actionb","actionc",
                 "actiond","actione","actionf",
                 "actiong","actionh","actioni"
                 ])
	}
}

def setTestValue(int val) {
	log.debug "setTestValue called $val"
    sendEvent("name":"testValue", "value":val)
}

def reportTestValue() {

	log.debug "reportTestValue called"
	
	def testValue = device.currentValue("testValue")
	
	if (testValue > 83) {
		sendEvent("name":"testValue", "value":67)
	} else {
		sendEvent("name":"testValue", "value":testValue+1)
	}
}

def setTestValue2(int val) {
	log.debug "setTestValue2 called $val"
    sendEvent("name":"testValue2", "value":val)
}

def  swingModeOn() {
    log.debug "swingModeOn pressed"
}


def  swingModeOff() {
    log.debug "swingModeOff pressed"
}


def  reset() {
    log.debug "reset pressed"
}

def  actionA() {
    log.debug "Action A pressed"
}

def  actionB() {
    log.debug "Action B pressed"
}

def  actionC() {
    log.debug "Action C pressed"
}

def  actionD() {
    log.debug "Action D pressed"
}

def  actionE() {
    log.debug "Action E pressed"
}

def  actionF() {
    log.debug "Action F pressed"
}

def  actionG() {
    log.debug "Action G pressed"
}

def  actionH() {
    log.debug "Action H pressed"
}

def  actionI() {
    log.debug "Action I pressed"
}

def reportTestValue2() {

	log.debug "reportTestValue2 called"
	
	def testValue = device.currentValue("testValue2")
	
	if (testValue > 83) {
		sendEvent("name":"testValue2", "value":67)
	} else {
		sendEvent("name":"testValue2", "value":testValue+1)
	}
}
	

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}