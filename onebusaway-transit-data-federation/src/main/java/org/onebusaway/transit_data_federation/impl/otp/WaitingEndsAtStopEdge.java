package org.onebusaway.transit_data_federation.impl.otp;

import org.onebusaway.transit_data_federation.services.transit_graph.StopEntry;
import org.opentripplanner.routing.algorithm.NegativeWeightException;
import org.opentripplanner.routing.core.State;
import org.opentripplanner.routing.core.TraverseOptions;
import org.opentripplanner.routing.core.TraverseResult;
import org.opentripplanner.routing.core.Vertex;

public class WaitingEndsAtStopEdge extends AbstractEdge {

  private final StopEntry _stop;

  public WaitingEndsAtStopEdge(GraphContext context, StopEntry stop) {
    super(context);
    _stop = stop;
  }

  @Override
  public Vertex getFromVertex() {

    throw new UnsupportedOperationException();
  }

  @Override
  public TraverseResult traverse(State s0, TraverseOptions options)
      throws NegativeWeightException {

    EdgeNarrativeImpl narrative = createNarrative(s0.getTime());

    return new TraverseResult(0, s0, narrative);
  }

  @Override
  public TraverseResult traverseBack(State s0, TraverseOptions options)
      throws NegativeWeightException {

    EdgeNarrativeImpl narrative = createNarrative(s0.getTime());

    return new TraverseResult(0, s0, narrative);
  }

  @Override
  public String toString() {
    return "WaitingEndsAtStopEdge(stop=" + _stop.getId() + ")";
  }

  private EdgeNarrativeImpl createNarrative(long time) {

    AlightVertex fromVertex = new AlightVertex(_context, _stop, time);
    WalkFromStopVertex toVertex = new WalkFromStopVertex(_context, _stop);
    return new EdgeNarrativeImpl(fromVertex, toVertex);
  }
}