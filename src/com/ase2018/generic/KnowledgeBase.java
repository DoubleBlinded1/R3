package com.ase2018.generic;

import java.util.Set;

public interface KnowledgeBase {

    Set<Set<Component>> getSubstitute(Set<Component> substituee);

    Set<Requirement>  getRecommendation(Requirement originalRequirement,Integer k);
}
