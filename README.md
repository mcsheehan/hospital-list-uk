# hospital-list-uk

The UX needs improving at the moment, because it assumes an internet connection is available and the nhs data download not being available is not handled (as per spec)
This however means that the first screen shows nothing for a few seconds whilst a background thread fetches the file from the network and parses it.

MVVM architecture has been implemented (however the model or repository part hasn't been completed)
I haven't had time to implement a repository / data layer for downloading the hospital data.
This could have even been stored locally in a roomdb or even just cached once.
This should be dependency injected into the viewmodel aiding testability and isolating the source of the data from the implementation, but I suppose time only exists so everything doesn't happen at once :).

This also means that I haven't done espresso tests as it would be nice to have a mock model to run these tests on and ensure the app flow is correct i.e. click on hospital item -> next page.

Normally I use github-flow for adding features, however for the initial architecture of the application I do not, which is why there aren't many git commits. Apologies.
My process was write the file downloader and CSV parser independently, with a few unit tests so that I can write the modules in isolation (and see that they ran)
Then add the Navigation host activity. Then design the hospital_card_item.xml and create the HospitalCardAdapter.

Press on a hospital item and see it magestically navigate to the next view (Navigation components have been implemented so the back stack is working and as a viewmodel has been used the
download doesn't need to reoccur upon rotating the screen.

## Building
You can build this project yourself or download the apk from the continuous integration, which has been setup with circleci

To build this project install android studio and android sdk version 29 and the latest build tools. Then press the hammer.

If you're feeling especially hipster you can twiddle your moustache and copy the commands from the .circleci/config.yml file and build it on the command line using gradle.
(This also involves installing android sdk command line tools)

However building the apk is optional as it is automatically released by the continuous integration system and a link to the apk provided.

## Side note
The hospital data file has changed considerably from the one bundled with the technical test (including file encoding) and delimiter, additionally there is a single corrupt line, which is handled.
