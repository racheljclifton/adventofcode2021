package aoc.day04;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 implements Day {

    @Override
    public String part1(List<String> input) {
        String[] drawnNumbers = input.get(0).split(",");
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < input.size() - 4; i += 6) {
            boards.add(new Board(input.subList(i, i + 5)));
        }
        for (int i = 0; i < 4; i++) {
            markBoards(drawnNumbers[i], boards);
        }
        Board winningBoard = null;
        int i = 3;
        while (winningBoard == null) {
            i++;
            markBoards(drawnNumbers[i], boards);
            winningBoard = findWinningBoard(boards);
        }
        int sumOfNonMarked = winningBoard.getLines().stream().filter(item -> !item.isDrawn()).map(item -> Integer.parseInt(item.getNumber())).reduce( 0, (subtotal, number) -> subtotal+=number);
        return String.valueOf(sumOfNonMarked * Integer.parseInt(drawnNumbers[i]));
    }

    @Override
    public String part2(List<String> input) {
        String[] drawnNumbers = input.get(0).split(",");
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < input.size() - 4; i += 6) {
            boards.add(new Board(input.subList(i, i + 5)));
        }
        for (int i = 0; i < 4; i++) {
            markBoards(drawnNumbers[i], boards);
        }
        int sumOfNonMarkedOnWinningBoard;
        int winningDraw;
        int i = 3;
        while (boards.size() > 1) {
            i++;
            markBoards(drawnNumbers[i], boards);
            findAndRemoveWinningBoards(boards);
        }
        boolean theLastBoardHasWon = false;
        while (!theLastBoardHasWon) {
            i++;
            markBoards(drawnNumbers[i], boards);
            Board winningBoard = findWinningBoard(boards);
            if(winningBoard != null) theLastBoardHasWon = true;
        }
        sumOfNonMarkedOnWinningBoard = boards.get(0).getLines().stream().filter(item -> !item.isDrawn()).map(item -> Integer.parseInt(item.getNumber())).reduce( 0, (subtotal, number) -> subtotal+=number);
        winningDraw = Integer.parseInt(drawnNumbers[i]);
        return String.valueOf(sumOfNonMarkedOnWinningBoard * winningDraw);
    }

    private Board findWinningBoard(List<Board> boards) {
        for(Board board : boards) {
            List<BoardItem> lines = board.getLines();
            for(int i = 0; i < lines.size() - 4; i += 5) {
                if (lines.get(i).isDrawn() && lines.get(i+1).isDrawn() && lines.get(i+2).isDrawn()
                        &&  lines.get(i+3).isDrawn() &&  lines.get(i+4).isDrawn()) {
                    return board;
                }
            }
            for(int i = 0; i < 5; i++) {
                if (lines.get(i).isDrawn() && lines.get(i+5).isDrawn() && lines.get(i+10).isDrawn()
                        &&  lines.get(i+15).isDrawn() &&  lines.get(i+20).isDrawn()) {
                    return board;
                }
            }
        }
        return null;
    }

    private void findAndRemoveWinningBoards(List<Board> boards) {
        List<Board> winningBoards = new ArrayList<>();
        for(Board board : boards) {
            List<BoardItem> lines = board.getLines();
            for(int i = 0; i < lines.size() - 4; i += 5) {
                if (lines.get(i).isDrawn() && lines.get(i+1).isDrawn() && lines.get(i+2).isDrawn()
                        &&  lines.get(i+3).isDrawn() &&  lines.get(i+4).isDrawn()) {
                    winningBoards.add(board);
                }
            }
            if(winningBoards.contains(board)) continue;
            for(int i = 0; i < 5; i++) {
                if (lines.get(i).isDrawn() && lines.get(i+5).isDrawn() && lines.get(i+10).isDrawn()
                        &&  lines.get(i+15).isDrawn() &&  lines.get(i+20).isDrawn()) {
                    winningBoards.add(board);
                }
            }
        }
        boards.removeAll(winningBoards);
    }

    private void markBoards(String number, List<Board> boards) {
        for (Board board: boards)
        {
            List<BoardItem> newLines = board.getLines().stream().peek(boardItem -> {
                if(boardItem.getNumber().equals(number)) {
                    boardItem.setDrawn(true);
                }
            }).collect(Collectors.toList());
            board.setLines(newLines);
        }
    }

    private static class Board {
        private List<BoardItem> lines;

        public Board(List<String> lineList) {
            lines = createBoardLine(lineList.get(0));
            lines.addAll(createBoardLine(lineList.get(1)));
            lines.addAll(createBoardLine(lineList.get(2)));
            lines.addAll(createBoardLine(lineList.get(3)));
            lines.addAll(createBoardLine(lineList.get(4)));
        }

        private List<BoardItem> createBoardLine(String line) {
            return Arrays.stream(line.split("\\s")).filter(item -> !item.isEmpty()).map(BoardItem::new).collect(Collectors.toList());
        }

        public List<BoardItem> getLines() {
            return lines;
        }

        public void setLines(List<BoardItem> lines) {
            this.lines = lines;
        }
    }

    private static class BoardItem {
        private final String number;
        private boolean drawn;

        public BoardItem(String number) {
            this.number = number;
            drawn = false;
        }

        public String getNumber() {
            return number;
        }

        public boolean isDrawn() {
            return drawn;
        }

        public void setDrawn(boolean drawn) {
            this.drawn = drawn;
        }
    }

}
