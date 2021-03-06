(defproject witan.ui "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3297"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [sablono "0.3.4"]
                 [garden "1.2.5"]
                 [org.omcljs/om "0.9.0"]
                 [prismatic/om-tools "0.3.11"]
                 [inflections "0.9.14"]
                 [prismatic/schema "1.0.0"]
                 [secretary "1.2.3"]
                 [datascript "0.11.6"]
                 [thi.ng/geom "0.0.881"]
                 [cljs-ajax "0.3.14"]
                 [cljs-log "0.2.1"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.5"]
            [lein-garden "0.2.6"]
            [lein-cljfmt "0.3.0"]
            [lein-ring "0.9.6"]]

  :source-paths ["src/clj" "src/cljc"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
              :builds [{:id "prod"
                        :source-paths ["src/cljs" "src/cljc"]
                        :compiler {:output-to "resources/public/js/compiled/witan-ui.js"
                                   :main witan.ui.core
                                   :optimizations :advanced
                                   :pretty-print false
                                   :warnings {:single-segment-namespace false}}}
                       {:id "dev"
                        :source-paths ["src/cljs" "src/cljc"]
                        :figwheel {:on-jsload "witan.ui.core/start-app!" }
                        :compiler {:main witan.ui.core
                                   :asset-path "js/compiled/out"
                                   :output-to "resources/public/js/compiled/witan-ui.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :source-map-timestamp true
                                   :warnings {:single-segment-namespace false}}}]}

  :figwheel {
             ;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler hello_world.server/handler
             }

  :garden {:builds [{;; Optional name of the build:
                     :id "ui"
                     ;; Source paths where the stylesheet source code is
                     :source-paths ["src/clj/styles"]
                     ;; The var containing your stylesheet:
                     :stylesheet witan.styles.base/base
                     ;; Compiler flags passed to `garden.core/css`:
                     :compiler {;; Where to save the file:
                                :vendors [:moz :webkit :o]
                                :output-to "resources/public/css/style.css"
                                ;; Compress the output?
                                :pretty-print? false}
                     }]}
  :ring {:handler witan.ring.handler/app}
  :profiles {:uberjar {:auto-clean false}}
  :uberjar-name "witan-ui.jar"
  :jvm-opts ["-Xmx2g"])

(comment
  (do (use 'figwheel-sidecar.repl-api)
      (cljs-repl)))
