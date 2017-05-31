# H1 Ron's Guide to installing the ZXT-120 device.

You can find a copy of the manual for this device here also in my github repo.  Note: This may not be your device's version so keep that in mind.  I find this version of the manual describes certain things quite well.  For example how to place the device into "Always Listening Mode.  Older versions of the manual that may come with your device have some errors in describing the steps.

[ZXT-120 Manual v1.0](https://github.com/gouldner/SmartThingsPublic/blob/master/devicetypes/gouldner/docs/zxt-120/rm-zxt-Zipato-Ir-Extender-User-Manual-v1.0.pdf)

First you must install the device code into your ide.  For instructions on how to do this see the very helpful FAQ on the community site.
[How to install Custom Device Code](https://community.smartthings.com/t/faq-an-overview-of-using-custom-code-in-smartthings/16772)
If you are too lazy to read this FAQ you will miss out on some excellent features for linking github to your ide for easy updates in the future etc.  But I understand if you only want to do this once for this device it is a lot to digest so here is the quick and dirty steps.
1. Get the raw code copy of the device code and copy into your copy/paste buffer.  [RAW COPY](https://raw.githubusercontent.com/gouldner/SmartThingsPublic/master/devicetypes/gouldner/zxt-120-ir-sender-improved.src/zxt-120-ir-sender-improved.groovy)
2. Open the ide and switch to the [device tab](https://graph.api.smartthings.com/ide/devices).
3. Select "+Create New Device Handler" button.
4. Switch to "From Code".  Paste code you copied from the Raw Code Page.
5. Press "Create" to save this code and create your copy of the custom device code.
6. You should now see a device handler in your device handler list with the label "gouldner : ZXT-120 IR Sender Improved" assuming you made no changes to the namespace or name of the device handler.

Now lets get ready to install the ZXT-120 into your system
The Steps are as follows.
1. Make sure your deviced in connected to a USB charger.  It works best this way.  Battery mode doesn't respond as needed so I recommend connecting the device to a wired charger at all times.  The next step requires a battery.  Continuous listening mode doesn't work with batteries according to the manual.
2. Make sure the device is in Continuous Listening Mode.  To do this follow the instructions in the [manual] (https://github.com/gouldner/SmartThingsPublic/blob/master/devicetypes/gouldner/docs/zxt-120/rm-zxt-Zipato-Ir-Extender-User-Manual-v1.0.pdf) on page 13/14.  
3. Now select add a device in the Smart Things App.  I am going to document how to do this because Smart Things likes to change the app very often moving things around making it impossible to really document how these things are done.  If you don't know how to add a device visit the [community](https://community.smartthings.com/) and search for instructions or contact Smart Things for support.
4. Once the Smart Things App is looking for devices press the button on the ZXT-120 so the Samrt Things Hud will see it and include the device.  The correct device Handler should be discovered as I have configured the fingerprint to match the device.  If it doesn't you may have to visit the "My Devices" page, select the ZXT-120 you just added, click edit and change the device handler to the "ZXT-120 IR Sender Improved" you created above.  NOTE: if you already included the device before installing the device handler you can switch it using this technique as well.

Now you need to configure your ZXT-120
< More instructions to come...sorry ran out of time... >




