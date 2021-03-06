# Theta

[![Build Status](https://travis-ci.org/FTSRG/theta.svg?branch=master)](https://travis-ci.org/FTSRG/theta)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/bc5270fd2ba2412bb5f4b81b42d4b9f8)](https://www.codacy.com/app/tothtamas28/theta?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=FTSRG/theta&amp;utm_campaign=Badge_Grade)
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache--2-brightgreen.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

## About

_Theta_ is a generic, modular and configurable model checking framework developed at the [Fault Tolerant Systems Research Group](http://inf.mit.bme.hu/en) of [Budapest University of Technology and Economics](http://www.bme.hu/?language=en), aiming to support the design and evaluation of abstraction refinement-based algorithms for the reachability analysis of various formalisms.
The main distinguishing characteristic of Theta is its architecture that allows the definition of input formalisms with higher level language front-ends, and the combination of various abstract domains, interpreters, and strategies for abstraction and refinement.

## Overview of the architecture

Theta can be divided into the following four layers.

* **Formalisms**: The core elements of Theta are the formalisms, which represent models of real life systems (e.g., software, hardware, protocols). Formalisms are usually low level, mathematical representations based on first order logic expressions and graph like structures. Formalisms can also support higher level languages that can be mapped to that particular formalism by a language front-end (consisting of a specific parser and possibly reductions for simplification of the model). The common features of the different formalisms reside in the [`core`](subprojects/core/README.md) project (e.g., expressions) and each formalism has its own project. Currently, there are three formalisms: symbolic transition systems ([`sts`](subprojects/sts/README.md)), control-flow automata ([`cfa`](subprojects/cfa/README.md)) and timed automata ([`xta`](subprojects/xta/README.md)).
* **Analysis back-end**: The analysis back-end provides the verification algorithms that can formally prove whether a model meets certain requirements. There is an interpreter for each formalism, providing a common interface towards the algorithms (e.g., calculating initial states and successors). This ensures that most components of the algorithms work for all formalisms (as long as they provide the interpreter). The verification algorithms are mostly based on abstraction. The analysis back-end defines various abstract domains, strategies for performing abstraction and refinement, and algorithms built from these components. The common components reside in the [`analysis`](subprojects/analysis/README.md) project (e.g., CEGAR loop) and the formalism-specific modules (e.g., the interpreters) are implemented in separate analysis projects (with suffix `-analysis`) for each formalism.
* **SMT solver interface and SMT solvers**: Many components of the algorithms rely on satisfiability modulo theories (SMT) solvers. The framework provides a general SMT solver interface in the project [`solver`](subprojects/solver/README.md) that supports incremental solving, unsat cores, and the generation of binary and sequence interpolants. Currently, the interface is implemented by the [Z3](https://github.com/Z3Prover/z3) SMT solver in the project [`solver-z3`](subprojects/solver-z3/README.md), but it can easily be extended with new solvers.
* **Tools**: Tools are command line or GUI applications that can be compiled into a runnable jar file. Tools usually read some input and then instantiate and run the algorithms. Tools are implemented in separate projects.

The table below shows the architecture and the projects. Each project contains a README.md in its root directory describing its purpose in more detail.

|                 | Common                | CFA                                 | STS            | XTA            |
|-----------------|-----------------------|-------------------------------------|----------------|----------------|
| **Tools**       |                       | [`cfa-cli`](subprojects/cfa-cli/README.md), [`cfa-metrics`](subprojects/cfa-metrics/README.md), [`cfa2dot`](subprojects/cfa2dot/README.md) | [`sts-cli`](subprojects/sts-cli/README.md)      | [`xta-cli`](subprojects/xta-cli/README.md)      |
| **Formalisms**  | [`core`](subprojects/core/README.md), [`common`](subprojects/common/README.md)      | [`cfa`](subprojects/cfa/README.md)                               | [`sts`](subprojects/sts/README.md)          | [`xta`](subprojects/xta/README.md)          |
| **Analyses**    | [`analysis`](subprojects/analysis/README.md)            | [`cfa-analysis`](subprojects/cfa-analysis/README.md)                      | [`sts-analysis`](subprojects/sts-analysis/README.md) | [`xta-analysis`](subprojects/xta-analysis/README.md) |
| **SMT solvers** | [`solver`](subprojects/solver/README.md), [`solver-z3`](subprojects/solver-z3/README.md) |

## Use Theta

Tools are concrete instantiations of the framework to solve a certain problem using the built in algorithms. Currently, the following 3 tools are available. Follow the links for more information about each tool.

* [`theta-cfa`](subprojects/cfa/README.md): Reachability checking of error locations in Control Flow Automata (CFA) using CEGAR-based algorithms.
* [`theta-sts`](subprojects/sts/README.md): Verification of safety properties in Symbolic Transition Systems (STS) using CEGAR-based algorithms.
* [`theta-xta`](subprojects/xta/README.md): Verification of Uppaal timed automata (XTA).

## Extend Theta

If you want to extend Theta and build your own algorithms and tools, then take look at [doc/Development.md](doc/Development.md).

## Read more

If you want to read more, take a look at the [list of publications](https://ftsrg.github.io/theta/publications/). A good starting point is our [tool paper](https://ftsrg.github.io/theta/publications/fmcad2017.pdf) and [slides](https://www.slideshare.net/AkosHajdu/theta-a-framework-for-abstraction-refinementbased-model-checking)/[talk](https://oc-presentation.ltcc.tuwien.ac.at/engage/theodul/ui/core.html?id=c658c37e-ae70-11e7-a0dd-bb49f3cb440c) presented at FMCAD 2017.

To cite Theta, please cite the following paper.

```
@inproceedings{theta-fmcad2017,
    author     = {T\'oth, Tam\'as and Hajdu, \'{A}kos and V\"or\"os, Andr\'as and Micskei, Zolt\'an and Majzik, Istv\'an},
    year       = {2017},
    title      = {Theta: a Framework for Abstraction Refinement-Based Model Checking},
    booktitle  = {Proceedings of the 17th Conference on Formal Methods in Computer-Aided Design},
    isbn       = {978-0-9835678-7-5},
    editor     = {Stewart, Daryl and Weissenbacher, Georg},
    pages      = {176--179},
    doi        = {10.23919/FMCAD.2017.8102257},
}
```

## Acknowledgements
Supporters of the Theta project are listed below.

* [MTA-BME Lendület Cyber-Physical Systems Research Group](http://lendulet.inf.mit.bme.hu/)
* [Fault Tolerant Systems Research Group](https://inf.mit.bme.hu/en), [Department of Measurement and Information Systems](https://www.mit.bme.hu/eng/), [Budapest University of Technology and Economics](http://www.bme.hu/?language=en)
* [Gedeon Richter’s](https://www.richter.hu/en-US/Pages/default.aspx) Talentum Foundation
* Nemzeti Tehetség Program, [Nemzet Fiatal Tehetségeiért Ösztöndíj 2016](http://www.emet.gov.hu/felhivasok/nemzeti_tehetseg_program212/) (NTP-NFTÖ-16)
* Nemzeti Tehetség Program, [Nemzet Fiatal Tehetségeiért Ösztöndíj 2018](http://www.emet.gov.hu/felhivasok/felhivas46/) (NTP-NFTÖ-18)
* [CECRIS project](http://www.cecris-project.eu/)
* [R5-COP project](http://www.r5-cop.eu/)
