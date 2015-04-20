import java.util.HashMap;

public class MiniMax 
{
	HashMap<Board, Integer> memoboard;
	
	int m_nMaxPly = 7;
	static int m_Twos;
	static int m_Threes;
	static int m_Fours;
	final int NEG_INF = Integer.MIN_VALUE;
	final int POS_INF = Integer.MAX_VALUE;
	
	//  This is the piece to search for. Should be either RED or YELLOW.
	int m_nSearchPiece = 0;
	
	// Set the piece for which a move will be found.
	public void SetSearchPiece( int nPiece )
	{
		m_nSearchPiece = nPiece;
	}

	// Compile a list of legal moves.
	int GetLegalMoves( int[][] nBoardData, int[] nMoveList )
	{
		int row, col;
		int nNumMoves = 0;

		// Loop through six rows.
		for( row=0; row<=5; row++ )
		{
			// Loop through seven columns.
			for( col=0; col<=6; col++ )
			{
				// If the board at this location is empty,
				//   then this square is a legal move.
				if( nBoardData[row][col] == Connect4.EMPTY &&
					( row == 5 || nBoardData[row+1][col] != Connect4.EMPTY ) )
				{
					// Put the row and column in the move list array.
					nMoveList[nNumMoves*2] = row;
					nMoveList[nNumMoves*2+1] = col;
					// Increment the index pointer.
					nNumMoves++;
				}
			}
		}

		// Return the number of legal moves that were found.
		return( nNumMoves );
	}

	int DoSearch( Position pos, int nPiece, int nDepth,  Board pBoard )
	{
		if(memoboard.containsKey(pBoard) && nDepth != 0)
			return memoboard.get(pBoard);
		
		// Local arrays for the legal move list and
		//   the result list.
		int[] nMoveList = new int[7*2];
		int[] nResultList = new int[7];
		// Create min and max variables.
		int nMin = 2000000, nMax = -2000000;

		// First, see if a side has won.
		if( pBoard.DidSideWin( nPiece ) )
		{
			if( nPiece == m_nSearchPiece )
			{
				return( 2000000 );
			}
			return( -2000000 );
		}
		
		// See if we have a Cats game.
		else if( pBoard.IsCatsGame() )
		{
			// Score for Cats game is 0.
			return( 0 );
		}
		
		// If we are at a leaf node, return the score.
		else if( nDepth >= m_nMaxPly )
		{		
			int nScore = ScoreIt(m_nSearchPiece, pBoard.GetBoardData());
			memoboard.put(pBoard, nScore);
			return nScore;
		}

		// Get the legal moves.
		int nMoves = GetLegalMoves( pBoard.GetBoardData(), nMoveList );

		if( nDepth == 0 )
		{
			pos.Row = nMoveList[0];
			pos.Col = nMoveList[1]; 
		}
		
		// Loop through the legal moves.
		for( int i=0; i<nMoves; i++ )
		{
			// We need a board clone so that we can place pieces
			//   without messing up previous board positions.
			Board SaveMe = pBoard.Clone();
			
			// Place the piece from the current move in the list.
			pBoard.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );
			
			// Call DoSearch() recursively.
			nResultList[i] = DoSearch( pos, nPiece ^ 1, nDepth + 1, pBoard );

			// Check to see if this result is less than the current min.
			if( nMin > nResultList[i] )
			{
				// Set nMin.
				nMin = nResultList[i];
			}
			
			// Check to see if this result is greater than the current max.
			if( nMax < nResultList[i] )
			{
				// Set nMax.
				nMax = nResultList[i];
				
				if( nDepth == 0 )
				{
					pos.Row = nMoveList[i*2];
					pos.Col = nMoveList[i*2+1]; 
				}
			}
			
			// Restore the board.
			pBoard = SaveMe.Clone();
		}
		
//		System.out.println( "" + nDepth + "," + ArrayToString( nResultList ) );

		// If this is max of minimax, then return the max.
		if( nPiece == m_nSearchPiece )
		{
			memoboard.put(pBoard, nMax);
			return( nMax );
		}
		
		// If this is min of minimax, then return the min.
		else
		{
			memoboard.put(pBoard, nMin);
			return( nMin );
		}
		
	}

	// Wrapper method that kicks off minimax to get a move.
	public void GetMove( Position pos, int[][] BoardData, int nPiece )
	{
		// Set the search piece.
		SetSearchPiece( nPiece );
		
		// Create a new board with this board data.
		Board brd = new Board();
		memoboard = new HashMap<Board, Integer>();
		brd.SetBoardData( BoardData );
		
		// Call the recursive method.
		DoSearch( pos, nPiece, 0, brd );
	}
	
	int ScoreIt( int nPiece, int[][] BoardData )
	{
		m_Twos = 0;
		m_Threes = 0;
		m_Fours = 0 ;
		
		for( int nRow=0; nRow<6; nRow++ )
		{
			int nCount = 0;
			for( int nCol=0; nCol<7; nCol++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					nCountCase(nCount);
					nCount = 0;
				}
			}
			nCountCase(nCount);
		}
		
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					nCountCase(nCount);
					nCount = 0;
				}
			}
			nCountCase(nCount);
		}
		

		
		// Loop through the diagonal data.
		for( int nDiagonalTest=0; nDiagonalTest<Board.m_nDiagonalData.length/4; nDiagonalTest++)
		{
			int nCount = 0;
			// Starting row.
			int nRow = Board.m_nDiagonalData[nDiagonalTest*4];
			// Starting column.
			int nCol = Board.m_nDiagonalData[nDiagonalTest*4+1];
			// YDirection for the iterations.
			int nYDir = Board.m_nDiagonalData[nDiagonalTest*4+2];
			// Number of iterations.
			int nIterations = Board.m_nDiagonalData[nDiagonalTest*4+3];

			// Loop through the iterations.
			for( int i=0; i<nIterations; i++ )
			{
				// If this is nSide then increment the counter.
				if( BoardData[nRow][nCol] == nPiece )
				{
					// Increment the counter.
					nCount++;
				}
				
				// This square does not equal nSide.
				else
				{
					if( nCount == 2 )
					{
						m_Twos++;
					}
					else if( nCount == 3 )
					{
						m_Threes++;
					}
					else if( nCount == 4 )
					{
						m_Fours++;
					}
					
					// Reset the counter.
					nCount = 0;
				}
				
				// Move the row position.
				nRow += nYDir;
				// Move the column position.
				nCol++;
			}
			
			if( nCount == 2 )
			{
				m_Twos++;
			}
			else if( nCount == 3 )
			{
				m_Threes++;
			}
			else if( nCount == 4 )
			{
				m_Fours++;
			}
			
		}

		int nPositionalAdvantage = 0;
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				nPositionalAdvantage += nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				nPositionalAdvantage += nCount;
			}
			
		}		
		return( nPositionalAdvantage + m_Twos + m_Threes * 2 + m_Fours * 4 );
	}

	void nCountCase(int nCount)
	{
		switch(nCount)
		{
		case 2:
			m_Twos++;
			break;
		case 3:
			m_Threes++;
			break;
		case 4:
			m_Fours++;
			break;
		}
	}
	
	
	static String ArrayToString( int[] IntArray )
	{
		String strRet = "{ ";
		for( int i=0; i<IntArray.length; i++ )
		{
			strRet += (""+IntArray[i] );
			if( i < IntArray.length - 1 )
			{
				strRet += ", ";
			}
		}
		return( strRet + " }");
	}
	
}

