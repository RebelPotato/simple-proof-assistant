(defn xors [max-x max-y]
  (for [x (range max-x) y (range max-y)]
    [x y (rem (bit-xor x y) 256)]))

(def size-x 500)
(def size-y 500)

(def frame (java.awt.Frame.))
(for [meth (.getMethods java.awt.Frame)
      :let [name (.getName meth)]
      :when (re-find #"Vis" name)]
  name)
(.setVisible frame true)
(.setSize frame (java.awt.Dimension. size-x size-y))
(def gfx (.getGraphics frame))

(doseq [[x y xor] (xors size-x size-y)]
  (.setColor gfx (java.awt.Color. xor xor xor))
  (.fillRect gfx x y 1 1))

(defn clear [g] (.clearRect g 0 0 size-x size-y))
(clear gfx)