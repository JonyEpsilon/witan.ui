# Witan.UI
     _    _ _ _               _   _ _____
    | |  | (_) |             | | | |_   _|
    | |  | |_| |_ __ _ _ __  | | | | | |
    | |/\| | | __/ _` | '_ \ | | | | | |
    \  /\  / | || (_| | | | || |_| |_| |_
     \/  \/|_|\__\__,_|_| |_(_)___/ \___/

## Overview

This is the frontend application that powers Witan, the open city planning tool, using...

[ClojureScript](https://github.com/clojure/clojurescript)  
[Om](https://github.com/omcljs/om)  
[Sablono](https://github.com/r0man/sablono)  
[Figwheel](https://github.com/bhauman/lein-figwheel)  
[Secretary](https://github.com/gf3/secretary)  
[Schema](https://github.com/Prismatic/schema)  
[Datascript](https://github.com/tonsky/datascript)  
[Garden](https://github.com/noprompt/garden)  

## To Develop

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/)
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

In a separate terminal, run:

    lein garden auto

This will update any chances to CSS styles (in the `styles` directory) and figwheel will automatically apply them.

To clean all compiled files:

lein clean

## To Build

To create a production build run:

    lein garden once
    lein cljsbuild once prod

You can use the in-built ring server which will automatically serve everything out of the `resources/public` directory (and redirect to `index.html`).

    lein ring server

Navigate to `http://localhost:3000` to view the app.

## License

Copyright © 2014 Mastodon C

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
