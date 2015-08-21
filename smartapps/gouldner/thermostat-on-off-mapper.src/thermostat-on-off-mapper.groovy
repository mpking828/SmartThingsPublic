/**
 *  Thermostat On/Off Mapper
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
    name: "Thermostat On-Off Mapper",
    namespace: "gouldner",
    author: "Ronald Gouldner",
    description: "Creates virtual switch which can be mapped to Cool, Heat or Dry",
    category: "Convenience",
    iconUrl: "http://baldeagle072.github.io/icons/standard-tile@1x.png",
    iconX2Url: "http://baldeagle072.github.io/icons/standard-tile@2x.png",
    iconX3Url: "http://baldeagle072.github.io/icons/standard-tile@3x.png")


preferences {
	section("Select the ZXT-120 Device... ") {
        input("thermostat", "capability.Thermostat", title: "ZXT-120", required: false)
        input(name: "modes", multiple: true, type: "enum", title: "Make Mode buttons", options: ["Cool","Dry","Heat"], required: true)
        // Note currently if basename is changed the virtual buttons don't change
        // Because I don't know how to do that.
        // However the virtual buttons can be renamed in the app so no big deal.
        input(name: "basename", type: "text", title: "Button Base Name", required: true)
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
    log.debug("initialize: modes=$modes,basename=$basename")
    def heatButtonRequested = modes?.contains("Heat")
    def coolButtonRequested = modes?.contains("Cool")
    def dryButtonRequested = modes?.contains("Dry")

    if (thermostat) {        
        log.debug("device selected setting up child devices and subscriptions")
        def heatDeviceId = app.id + "Heat"
        def heatDevice = getChildDevice(heatDeviceId)
        if (!heatDevice && heatButtonRequested) {
            def heatLabel = basename + " Heat"
            log.debug "Creating Device $heatLabel of type Stateless On/Off Button Tile"
            heatDevice = addChildDevice("gouldner", "Stateless On/Off Button Tile", heatDeviceId, null, [label: heatLabel])
        }
        else if (heatDevice) {
            if (heatButtonRequested) {
                subscribe(heatDevice, "switch.on", heatOnHandler)
                subscribe(heatDevice, "switch.off", heatOffHandler)
            } else {
                // Note this code fails with "You are not authorized to perform this action"
                // I don't understand why but I think the unsubscribe isn't fast enough
                deleteChildDevice(app.id + "Heat")
            }
        }
        
        def dryDeviceId = app.id + "Dry"
        def dryDevice = getChildDevice(dryDeviceId)
        if (!dryDevice && modes?.contains("Dry")) {
            def dryLabel = basename + " Dry"
            log.debug "Creating Device $dryLabel of type Stateless On/Off Button Tile"
            heatDevice = addChildDevice("gouldner", "Stateless On/Off Button Tile", dryDeviceId, null, [label: dryLabel])
        }
        else if (dryDevice) {
            if (dryButtonRequested) {
                subscribe(dryDevice, "switch.on", dryOnHandler)
                subscribe(dryDevice, "switch.off", dryOffHandler)
            } else {
                // Note this code fails with "You are not authorized to perform this action"
                // I don't understand why but I think the unsubscribe isn't fast enough
                deleteChildDevice(app.id + "Dry")
            }
        }
        
        def coolDeviceId = app.id + "Cool"
        def coolDevice = getChildDevice(coolDeviceId)
        if (!coolDevice && modes?.contains("Cool")) {
            def coolLabel = basename + " Cool"
            log.debug "Creating Device $coolLabel of type Stateless On/Off Button Tile"
            coolDevice = addChildDevice("gouldner", "Stateless On/Off Button Tile", coolDeviceId, null, [label: coolLabel])
        }


        else if (coolDevice) {
            if (coolButtonRequested) {
                subscribe(coolDevice, "switch.on", coolOnHandler)
                subscribe(coolDevice, "switch.off", coolOffHandler)
            } else {
                // Note this code fails with "You are not authorized to perform this action"
                // I don't understand why but I think the unsubscribe isn't fast enough
                deleteChildDevice(app.id + "Cool")
                
            }
        }
    } else {
        // Note: removing children seem to fail when subscriptions exist
        //       subscriptions should have been removed by updated() method
        //       which calls this initialize after first install
        //       but for some reason the subscriptions remain so wait a bit
        //       then remove children
        // V1 possible but fails V2 not possible
        // Fails if reached V1
        removeAllChildDevices(getChildDevices()) 
    }
}

def uninstalled() { 
    log.debug "uninstalled"
    // Unsubscribe (should happen automatically but causes remove children to fail
    // if we don't do it here 
    unsubscribe()
    // let unsubscribe finish before removing children
    // Tim Slagle says I don't need this the server will remove children
    //runIn(30, removeAllChildDevices)
}

def removeAllChildDevices(delete) {
    log.debug "removeAllChildDevices"
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}

private removeChildDevices(delete) {
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}

def heatOnHandler(evt) {
    log.debug "setting $thermostat.label to Heat Mode"
	thermostat.heat()
}

def heatOffHandler(evt) {
    log.debug "setting $thermostat.label to Heat Off"
	thermostat.off()
}

def coolOnHandler(evt) {
    log.debug "setting $thermostat.label to Cool Mode"
	thermostat.cool()
}

def coolOffHandler(evt) {
    log.debug "setting $thermostat.label to Cool Off"
	thermostat.off()
}

def dryOnHandler(evt) {
    log.debug "setting $thermostat.label to Dry Mode"
	thermostat.dry()
}

def dryOffHandler(evt) {
    log.debug "setting $thermostat.label to Dry Off"
	thermostat.off()
}