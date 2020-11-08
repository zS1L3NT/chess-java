package board;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    public int position;
    public Team team;

    public Piece(int position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract List<Move> legal_moves(Board board);

    public List<Move> safe_moves(Board board) {
        List<Move> legal_moves = legal_moves(board);
        List<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < legal_moves.size(); i++) {
            Move move = legal_moves.get(i);
            Board simulation = move.simulate(board);
            if (this.team.king_is_safe(simulation))
                moves.add(move);
        }
        return moves;
    }

    public List<Integer> safe_positions(Board board) {
        List<Integer> tiles = new ArrayList<Integer>();
        List<Move> safe_moves = safe_moves(board);
        for (int i = 0; i < safe_moves.size(); i++) {
            Move move = safe_moves.get(i);
            tiles.add(move.destination);
        }
        return tiles;
    }

    public void set_position(int position) {
        this.position = position;
    }

    @Override
    public abstract String toString();
    
    
	public abstract String boardKey();
}
