; 这是clojure的主程序
;; (comment
;;   ; 一段证明可能像这样：
;;   (prove '[:implies [:and [:implies A B] A] B]
;;          ax-3
;;          QED) 
;;   )

; all truth are stored like this:
; [type content name1 name2 ...]
; type and content are keywords
; names can be strings

(def development-truth
  #{{:type :unitary, :content :not
     :print-symbol "-."}

    {:type :binary, :content :implies
     :print-symbol "->"}
    {:type :binary, :content :and
     :print-symbol "/\\"}

    {:type :setvar, :content :a}
    {:type :setvar, :content :b}
    {:type :setvar, :content :c}
    {:type :setvar, :content :x}
    {:type :setvar, :content :y}
    {:type :setvar, :content :z}

    {:type :wff, :content :phi}
    {:type :wff, :content :theta}
    {:type :wff, :content :psi}
    {:type :wff, :content :chi}

    ;; {:type :axiom
    ;;  :content [:implies :phi [:implies :psi :phi]]
    ;;  :name ["simp"]}
    ;; {:type :axiom
    ;;  :content [:implies [:implies :phi [:implies :psi :chi]] [:implies [:implies :phi :psi] [:implies :phi :chi]]]
    ;;  :name ["Frege"]}
    ;; {:type :axiom
    ;;  :content [:implies [:implies [:not :phi] [:not :psi]] [:implies :psi :phi]]
    ;;  :name ["Transp"]}
    {:type :axiom
     :content [:implies :phi [:implies :psi :phi]]
     :use-name :ax-1
     :name ["simp"]}
    {:type :axiom
     :content [:implies [:implies :phi [:implies :psi :chi]] [:implies [:implies :phi :psi] [:implies :phi :chi]]]
     :use-name :ax-2
     :name ["Frege"]}
    {:type :axiom
     :content [:implies [:implies [:not :phi] [:not :psi]] [:implies :psi :phi]]
     :use-name :ax-3
     :name ["Transp"]}})

(def known-truth development-truth)
(defn load-truth
  "Load all known truth, including axioms, theorems and other things."
  []
  nil)
(defn save-truth
  "Save the truth somewhere."
  []
  nil)

(def form-symbol-type
  #{:unitary :binary :setvar :wff})

(defn form-symbol?
  "Check if a certain keyword is a symbol, and return the result."
  [p]
  ((first (filter #(= p (% :content)) known-truth)) :type)
  )

(def goal-stack [])

; all axioms and theorems are well formed.
; well-formed formulas have the structure:
; []
;; (defn wff? 
;;   "Check if a given structure is a well formed formula."
;;   [s]
;;   (if (and (vector? s) (filter)) nil)
;;   )

(defn proof
  "Begin the proof of the given theorem. Pass down both the truth stack and the goal stack."
  ; perhaps I should sort the truth set.
  [t]
  (conj goal-stack t))


(defn find-sentence
  "find an axiom or an theorem"
  [sentence-name]
  ((first (filter #(= (% :use-name) sentence-name) known-truth)) :content))

(defn rewrite-form
  "Use a form to rewrite the target, which may include a custom rewriting method"
  [form target]
  ; if the form is of the form of A->B, then try to match the goal with B
  ; else, generate a goal A->B 
  )

(defn rewrite
  "Use a form to rewrite the top of the goal stack, which may include a custom rewriting method"
  []
  )

(defn assume
  ""
  []
  )

(->> [:implies :phi :phi]
    proof
     (rewrite ()))
