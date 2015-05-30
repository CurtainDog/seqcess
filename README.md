# seqcess

[![Clojars Project](http://clojars.org/seqcess/latest-version.svg)](http://clojars.org/seqcess)

A collection of utilities dealing with the seq abstraction

Note on pronounciation: the official pronounciation of seq
in any of these utilities is in the manner that would render
the pun most effective, i.e.

* **seqcess:** *sek-CESS*
* **seqing:** *SEEK-ing*
* **seqer:** *SEEK-er*
* **quest:** *ummm, it's just quest*

## Components

### seqing arrow

seq-> is a generalised form of the threading macros available in clojure.core.
The motivation for its design was to be able to inspect the intermediate
results of a threading operation.

seq-> can be used in a manner very similar to as-> except that the name
is initially bound to nil, and thereafter bound to the entire sequence
of results rather than the latest result. Similarly the return is this
entire sequence.

### quest (TBA)

*SEQ-based Unit Tests*

quest is a ground up redesign of a minimal test framework. It is designed
to be heavily sequence based and enable tests that resemble regular
production code.

### seqer (TBA)

*Seqer, and ye shall find!*

A seqer is a cousin to a transducer. Unlike the transducer, which is
concerned primarily with function composition, the seqer uses the seq
as its compositional primitive.

## License

Copyright © 2015 Richard Cordova

Distributed under the Eclipse Public License either version 1.0.
