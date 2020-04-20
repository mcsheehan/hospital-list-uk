# hospital-list-uk

## Implementation
MVVM architecture has been implemented (however the model or repository part hasn't been completed)

Jetpack and Android architecture components have been used to build this app the res/navigation/navigation_graph.xml is a good place to look at, so get an overview of what has been done and how data
is passed between views.

My process was write the file downloader and CSV parser independently, with a few unit tests so that I could write these modules in isolation (and see that they ran)
Then add the Navigation host activity. Then design the hospital_card_item.xml and create the HospitalCardAdapter. I have a template project with the libraries I typically use,
so I drew heavily from that to begin the project.

The backstack is implemented as a byproduct of using the navigation architecture component.

Additionally the filter button adds and removes itself to the menu bar upon view change.

## Usage
Wait about 3-4 seconds for the download to complete and parse ->

Press on a hospital item and see it majestically navigate to the next view. Navigation components have been implemented so the back stack is working and as a viewmodel has been used the
download doesn't need to reoccur upon rotating the screen.

So rotate the screen. Additionally the filter button adds and removes itself from the action bar for the relevant navigation view, and shows the hospitals listed as in the NHS sector or not.

## Notes for improvement
The UX needs improving at the moment, as an internet connection is assumed and the hospital data download error case is not handled (as per spec)

To improve the UX some user feedback should occur to show that the file is being downloading and parsed, currently it's just an empty screen.

I haven't had time to implement a repository / data layer for downloading the hospital data.
This could be downloaded once and stored locally in a roomdb or even just the file cached.
The model should be dependency injected into the viewmodel aiding testability and isolating the source of the data from the implementation, but time only exists so everything doesn't happen at once :).

This also means that I haven't done espresso tests as it would be nice to have a mock model to run these tests on and ensure the app flow is correct i.e. click on hospital item -> next page.

Normally I use github-flow for adding features, however for the initial architecture of the application I do not, which is why there aren't many git commits. Apologies.

## Building
You can build this project yourself or download the apk from the continuous integration, which has been setup with circleci

To build this project install android studio and android sdk version 29 and the latest build tools. Then press the hammer.

If you're feeling especially hipster you can twiddle your moustache and copy the commands from the .circleci/config.yml file and build it on the command line using gradle.
(This also involves installing android sdk command line tools)

However building the apk is optional as it is automatically released by the continuous integration system and a link to the apk provided.

## Side note
The hospital data file has changed considerably from the one bundled with the technical test (including file encoding) and delimiter, additionally there is a single corrupt line, which is handled.
