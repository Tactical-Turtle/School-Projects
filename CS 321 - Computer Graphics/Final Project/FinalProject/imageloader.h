// Represents an image
class Image 
{
	public:
		Image(char* ps, int w, int h);
		~Image();
	
		char* pixels;
		int width;
		int height;
};

//Reads a bitmap (.bmp) image from file.
Image* loadBMP(const char* filename);

