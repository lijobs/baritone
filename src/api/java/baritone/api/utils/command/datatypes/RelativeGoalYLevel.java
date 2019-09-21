/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.api.utils.command.datatypes;

import baritone.api.pathing.goals.GoalYLevel;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.command.exception.CommandException;
import baritone.api.utils.command.helpers.arguments.ArgConsumer;

import java.util.stream.Stream;

public class RelativeGoalYLevel implements IDatatypePost<GoalYLevel, BetterBlockPos> {

    final RelativeCoordinate coord;

    public RelativeGoalYLevel() {
        coord = null;
    }

    public RelativeGoalYLevel(ArgConsumer consumer) throws CommandException {
        coord = consumer.getDatatype(RelativeCoordinate.class);
    }

    @Override
    public GoalYLevel apply(BetterBlockPos origin) {
        return new GoalYLevel(coord.applyFloor(origin.y));
    }

    @Override
    public Stream<String> tabComplete(ArgConsumer consumer) {
        if (consumer.hasAtMost(1)) {
            return consumer.tabCompleteDatatype(RelativeCoordinate.class);
        }
        return Stream.empty();
    }
}