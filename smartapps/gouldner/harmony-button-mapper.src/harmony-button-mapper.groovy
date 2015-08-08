/**
 *  Harmony Button Mapper
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
definition(
    name: "Harmony Button Mapper",
    namespace: "gouldner",
    author: "Ronald Gouldner",
    description: "Creates virtual switches mapped to Harmony Activities",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Partner/harmony.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Partner/harmony@2x.png")


preferences {
	section("Select the Harmony Hub To Map"){
		input "mediaController", "capability.mediaController", title: "Harmon Hub", required: false, multiple: false
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

def initialize() {
    if (mediaController) {        
        log.debug "device selected setting up child devices and subscriptions"
        def activities = mediaController.getAllActivities()
        log.debug "Activites returned by media Controller = $activities"
/*
        def activityId = app.id + "Activity"
        def heatDevice = getChildDevice(heatDeviceId)
        if (!heatDevice) {
		    def heatLabel = thermostat.label + " Heat"
		    log.debug "Creating Device $heatLabel of type Stateless On/Off Button Tile"
            heatDevice = addChildDevice("gouldner", "Stateless On/Off Button Tile", heatDeviceId, null, [label: heatLabel])
        }
	subscribe(heatDevice, "switch.on", heatOnHandler)
	subscribe(heatDevice, "switch.off", heatOffHandler)
*/
    } else {
        // Note: removing children seem to fail when subscriptions exist
        //       subscriptions should have been removed by updated() method
        //       which calls this initialize after first install
        //       but for some reason the subscriptions remain so wait a bit
        //       then remove children
        runIn(300, removeAllChildDevices)
    }
}

def uninstalled() {
    unsubscribe()
    runIn(300, removeAllChildDevices)
}

def removeAllChildDevices() {
    removeChildDevices(getChildDevices())
}

private removeChildDevices(delete) {
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}

/*
def heatOnHandler(evt) {
    log.debug "setting $thermostat.label to Heat Mode"
	thermostat.heat()
}

def heatOffHandler(evt) {
    log.debug "setting $thermostat.label to Heat Off"
	thermostat.off()
}
*/