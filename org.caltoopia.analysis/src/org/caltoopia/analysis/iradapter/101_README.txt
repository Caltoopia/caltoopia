org.caltoopia.analysis.iradapter
================================

Implementation of the analysis intermediate representation (AIR) using caltoopia IR classes.

Implementation of AIR interface as adapters/wrappers of caltoopia.ir objects:
CaltoopiaNetwork              Implements air.Network using org.caltoopia.ir.Network 
CaltoopiaActorInstance        Implements ActorInstance using a org.caltoopia.ir.ActorInstance and
                              AbstractActor pair (for the actor instance and the class, respectively).
CaltoopiaActorImplementation  Implements ActorImplementation a org.caltoopia.ir.ActorInstance and
                              Actor pair (for the actor instance and the class, respectively).
CaltoopiaAction               Implements Action as a wrapper of org.caltoopia.ir.Action.
CaltoopiaTransition           Implements Transition using State and CaltoopiaAction.
CaltoopiaGuard                Implements Guard as a wrapper of an org.caltoopia.ir.Guard list.
CaltoopiaStateVariable        Implements StateVariable as a wrapper of org.caltoopia.ir.Variable.

Priority of actions and transitions is represented using:
AbstractPriorityRelation<T>   A priority relation among elements of some (parameterized) domain, T.
CaltoopiaActionPriority       Here, the domain is T=CaltoopiaAction; a wrapper for org.caltoopia.PriorityGraph 
                              and Schedule.
PerStatePriorityRelation      Implements air.PriorityRelation, maps the CaltoopiaActionPriority to the
                              domain of transitions (rather than actions) and restricts the relation to the
                              transitions from a particular state.
                              
Straight-forward ("vanilla") implementation of AIR interfaces as aggregation of other interfaces:
VanillaActorSchedule          Implements ActorSchedule (FSM).
VanillaFsmState               Implements State (of the FSM)
VanillaConnection             Implements Connection (between ActorInstances)
VanillaPortInstance           Implements PortInstance (endpoint of Connection)


