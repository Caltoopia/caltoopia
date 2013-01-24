analysis.air
==============

1. Rationale
AIR = Analysis IR, which is the API used by the analysis.* classes. 

Why not use caltoopia IR directly?

Some of the analyses are legacy from the ACTORS, M5 and SNOW projects and predated caltoopia, 
so parts already existed. Although adding an extra glue layer, we think that it is still, today, 
a good idea to insulate the analysis from caltoopia:  
a) not taint the implementation with Eclipse specifics (e.g. requiring it to be an Eclipse plug-in).
b) to compensate for changes in the caltoopia IR, which is still in evolution and 
c) provide additional "good-to-haves" that are currently not supported by the caltoopia IR. 
d) facilitate use with an alternate CAL front end (perhaps even another dataflow language).

2. Overview
Four interfaces provide the fundamentals:
* Network,             which represents an elaborated network of actors.
* ActorInstance,       which represents an actor instance, a node in the network.
* Connection,          which represents a connection between two port instances.
* PortInstance,        which represents the end-point of a connection.

At a finer level of detail, there are interfaces describing the implementation of an actor. Not all actor may be
associated with such details. The implementation of an external actor would, for instance, not be known.
* ActorImplementation, which is a container for the detailed information regarding an ActorInstance.
* Action,              which represents an action
* StateVariable,       which represents a variable of the actor
* ActorSchedule,       which represents the finite state machine (FSM) that governs action selection

The ActorSchedule is further specified using
* State,               which represents an FSM state
* Transition,          which represents a transition between two states
* Guard,               which represents a guard condition
* PriorityRelation,    which specifies the partial ordering of transitions by priority
Note that Guards are associated with Transitions (rather than actions) and that the priority relation is 
expressed in terms of Transitions.

An Action is further specified using
* PortSignature,       which represents the consumption/production rates (a mapping from port to rate).
* InputLookAhead,      which represents look-ahead into a stream of input tokens (used to characterize the
                       input dependence of a Guard)  

3. Usage
A Network object is the starting point, from which the entire intermediate representation can be retrieved;
it is an aggregation of ActorInstances and Connections. An ActorInstance optionally has an ActorImplementation, 
by which Actions and ActorSchedules can be retrieved.

How to get a network from the front end
a) Eclipse plug-in
b) Stand-alone front end